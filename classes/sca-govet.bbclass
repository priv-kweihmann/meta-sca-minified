## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

## Add ids to suppress on a recipe level
SCA_GOVET_EXTRA_SUPPRESS ?= ""
## Add ids to lead to a fatal on a recipe level
SCA_GOVET_EXTRA_FATAL ?= ""
## File extension filter list (whitespace separated)
SCA_GOVET_FILE_FILTER ?= ".go"

SCA_RAW_RESULT_FILE[govet] = "txt"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-suppress
inherit sca-tracefiles

def do_sca_conv_govet(d):
    import os
    import re
    import hashlib
    
    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")

    items = []
    ## We use 2 pattern to catch compiler err and go vet warnings
    pattern_warn = r"^(vet:\s+)*(?P<file>.*):(?P<line>\d+):\s*(?P<msg>.*)"
    pattern_err = r"^(vet:\s+)*(?P<file>.*):(?P<line>\d+):(?P<col>\d+):\s*(?P<msg>.*)"

    _suppress = sca_suppress_init(d)
    _findings = []

    if os.path.exists(sca_raw_result_file(d, "govet")):
        with open(sca_raw_result_file(d, "govet"), "r") as f:
            content = f.read()
            for m in re.finditer(pattern_warn, content, re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            Tool="govet",
                                            BuildPath=buildpath,
                                            File=os.path.join(d.getVar("TOPDIR"), m.group("file")),
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID=hashlib.md5(str.encode(m.group("msg"))).hexdigest(),
                                            Severity="warning")
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings.append(g)
                except Exception as e:
                    bb.warn(str(e))
            for m in re.finditer(pattern_err, content, re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            Tool="govet",
                                            BuildPath=buildpath,
                                            File=os.path.join(d.getVar("TOPDIR"), m.group("file")),
                                            Column=m.group("col"),
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID=hashlib.md5(str.encode(m.group("msg"))).hexdigest(),
                                            Severity="error")
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings.append(g)
                except Exception as e:
                    bb.warn(str(e))
    sca_add_model_class_list(d, _findings)
    return sca_save_model_to_string(d)

python do_sca_govet() {
    import os
    import subprocess
    d.setVar("SCA_EXTRA_SUPPRESS", d.getVar("SCA_GOVET_EXTRA_SUPPRESS"))
    d.setVar("SCA_EXTRA_FATAL", d.getVar("SCA_GOVET_EXTRA_FATAL"))
    d.setVar("SCA_SUPRESS_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE", True), "govet-{}-suppress".format(d.getVar("SCA_MODE"))))
    d.setVar("SCA_FATAL_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE", True), "govet-{}-fatal".format(d.getVar("SCA_MODE"))))

    os.environ["GOCACHE"] = d.expand("${T}/.gocache")
    _args = ["go", "vet", "-v", "-all"]

    cmd_output = ""
    _files = get_files_by_extention(d,    
                                    d.getVar("SCA_SOURCES_DIR"),    
                                    clean_split(d, "SCA_GOVET_FILE_FILTER"),    
                                    sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA")))

    ## Run
    if any(_files):
        try:
            cmd_output = subprocess.check_output(_args + _files, universal_newlines=True, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            cmd_output = e.stdout or ""
    with open(sca_raw_result_file(d, "govet"), "w") as o:
        o.write(cmd_output)
}

do_sca_govet_report[vardepsexclude] += "TOPDIR"
python do_sca_govet_report() {
    import os
    ## Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/govet.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_govet(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "govet", get_fatal_entries(d))
}

SCA_DEPLOY_TASK = "do_sca_deploy_govet"

python do_sca_deploy_govet() {
    sca_conv_deploy(d, "govet")
}

do_sca_govet[doc] = "Lint go files with go vet"
do_sca_govet_report[doc] = "Report findings of do_sca_govet"
do_sca_deploy_govet[doc] = "Deploy results of do_sca_govet"
addtask do_sca_govet after do_configure before do_sca_tracefiles
addtask do_sca_govet_report after do_sca_tracefiles
addtask do_sca_deploy_govet after do_sca_govet_report before do_package

DEPENDS += "govet-sca-native sca-recipe-govet-rules-native"
