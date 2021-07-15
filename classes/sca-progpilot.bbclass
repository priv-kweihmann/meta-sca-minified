## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

## Add ids to suppress on a recipe level
SCA_PROGPILOT_EXTRA_SUPPRESS ?= ""
## Add ids to lead to a fatal on a recipe level
SCA_PROGPILOT_EXTRA_FATAL ?= ""
SCA_PROGPILOT_FILE_FILTER ?= ".php"

SCA_RAW_RESULT_FILE[progpilot] = "json"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-suppress
inherit sca-tracefiles

def do_sca_conv_progpilot(d):
    import os
    import json
    
    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")
    
    _findings = []
    _suppress = sca_suppress_init(d)

    if os.path.exists(sca_raw_result_file(d, "progpilot")):
        content = []
        with open(sca_raw_result_file(d, "progpilot"), "r") as f:
            try:
                content = json.load(f)
            except json.JSONDecodeError:
                pass
            for m in content:
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            BuildPath=buildpath,
                                            Tool="progpilot",
                                            File=m["sink_file"],
                                            Line=str(m["sink_line"]),
                                            Column=str(m["sink_column"]),
                                            Message="{}: {}".format(m["vuln_name"], m["vuln_type"]),
                                            ID=m["vuln_cwe"],
                                            Severity="error")
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings.append(g)
                except Exception as exp:
                    bb.warn(str(exp))

    sca_add_model_class_list(d, _findings)
    return sca_save_model_to_string(d)

python do_sca_progpilot() {
    import os
    import subprocess
    d.setVar("SCA_EXTRA_SUPPRESS", d.getVar("SCA_PROGPILOT_EXTRA_SUPPRESS"))
    d.setVar("SCA_EXTRA_FATAL", d.getVar("SCA_PROGPILOT_EXTRA_FATAL"))
    d.setVar("SCA_SUPRESS_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE", True), "progpilot-{}-suppress".format(d.getVar("SCA_MODE"))))
    d.setVar("SCA_FATAL_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE", True), "progpilot-{}-fatal".format(d.getVar("SCA_MODE"))))

    cmd_output = ""

    ## Run
    _args = [os.path.join(d.getVar("STAGING_BINDIR_NATIVE"), "progpilot/vendor/bin/progpilot")]
    _args += ["--ansi"]
    _args += ["-n"]
    
    _files = get_files_by_extention_or_shebang(d, d.getVar("SCA_SOURCES_DIR"), ".*php", [".php"], \
                                                sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA")))
    
    if any(_files):    
        try:
            cmd_output += subprocess.check_output(_args + _files, universal_newlines=True, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            cmd_output += e.stdout or ""

    with open(sca_raw_result_file(d, "progpilot"), "w") as o:
        o.write(cmd_output)
}

python do_sca_progpilot_report() {
    import os
    ## Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/progpilot.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_progpilot(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "progpilot", get_fatal_entries(d))
}

SCA_DEPLOY_TASK = "do_sca_deploy_progpilot"

python do_sca_deploy_progpilot() {
    sca_conv_deploy(d, "progpilot")
}

do_sca_progpilot[doc] = "Lint php scripts with progpilot"
do_sca_progpilot_report[doc] = "Report findings of do_sca_progpilot"
do_sca_deploy_progpilot[doc] = "Deploy results of do_sca_progpilot"
addtask do_sca_progpilot after do_configure before do_sca_tracefiles
addtask do_sca_progpilot_report after do_sca_tracefiles
addtask do_sca_deploy_progpilot after do_sca_progpilot_report before do_package

DEPENDS += "progpilot-native sca-recipe-progpilot-rules-native"
