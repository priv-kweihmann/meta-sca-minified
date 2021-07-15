## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

SCA_DETECTSECRETS_EXTRA_SUPPRESS ?= ""
SCA_DETECTSECRETS_EXTRA_FATAL ?= ""

SCA_RAW_RESULT_FILE[detectsecrets] = "json"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-license-filter
inherit sca-suppress

inherit python3native

DEPENDS += "python3-detect-secrets-native"

def do_sca_conv_detectsecrets(d):
    import os
    import json
    
    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")

    items = []
    __excludes = sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA"))
    __suppress = sca_suppress_init(d)
    _findings = []

    if os.path.exists(sca_raw_result_file(d, "detectsecrets")):
        j = {}
        with open(sca_raw_result_file(d, "detectsecrets")) as i:
            try:
                j = json.load(i)
            except:
                pass
        if "results" in j.keys():
            for k, v in j["results"].items():
                if k in __excludes:
                    continue
                for w in v:
                    try:
                        g = sca_get_model_class(d,
                                                PackageName=package_name,
                                                Tool="detectsecrets",
                                                File=k,
                                                BuildPath=buildpath,
                                                Line=str(w["line_number"]),
                                                Message="{} found: {}".format(w["type"], w["hashed_secret"]),
                                                ID=w["type"].replace(" ", ""),
                                                Severity="warning")
                        if __suppress.Suppressed(g):
                            continue
                        if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                            continue
                        if g.Severity in sca_allowed_warning_level(d):
                            _findings.append(g)
                    except Exception as exp:
                        bb.warn(str(exp))

    sca_add_model_class_list(d, _findings)
    return sca_save_model_to_string(d)

python do_sca_detectsecrets_core() {
    import os
    import subprocess
    import json

    d.setVar("SCA_EXTRA_SUPPRESS", d.getVar("SCA_DETECTSECRETS_EXTRA_SUPPRESS"))
    d.setVar("SCA_EXTRA_FATAL", d.getVar("SCA_DETECTSECRETS_EXTRA_FATAL"))
    d.setVar("SCA_SUPRESS_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "detectsecrets-{}-suppress".format(d.getVar("SCA_MODE"))))
    d.setVar("SCA_FATAL_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "detectsecrets-{}-fatal".format(d.getVar("SCA_MODE"))))

    _args = [d.getVar("PYTHON")]
    _args += [os.path.join(d.getVar("STAGING_BINDIR_NATIVE"), "detect-secrets")]
    _args += ["scan"]
    _args += ["--all-files"]
    _args += ["--use-all-plugins"]
    _files = get_files_by_extention(d, d.getVar("SCA_SOURCES_DIR"), "",
                                sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA")))

    cmd_output = ""
    if any(_files):
        try:
            cmd_output = subprocess.check_output(_args + _files, universal_newlines=True, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            cmd_output = e.stdout or ""

    with open(sca_raw_result_file(d, "detectsecrets"), "w") as o:
        o.write(cmd_output)
}

python do_sca_detectsecrets_core_report() {
    import os
    ## Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/detectsecrets.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_detectsecrets(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "detectsecrets", get_fatal_entries(d))
}
