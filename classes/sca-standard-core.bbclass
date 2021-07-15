## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

SCA_STANDARD_EXTRA_SUPPRESS ?= ""
SCA_STANDARD_EXTRA_FATAL ?= ""
SCA_STANDARD_FILE_FILTER ?= ".js .jsx"

SCA_RAW_RESULT_FILE[standard] = "txt"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-license-filter
inherit sca-suppress
inherit sca-image-backtrack

DEPENDS += "standard-native"

def do_sca_conv_standard(d):
    import os
    import re

    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")

    pattern = r"^(?P<file>.*):(?P<line>\d+):(?P<col>\d+):\s+(?P<msg>.*)\s+\((?P<id>.*)\)"
    pattern_parser = r"^(?P<file>.*?):(?P<line>\d+):(?P<col>\d+):\s+Parsing error:\s+(?P<msg>.*)\s+\(.*"

    _suppress = sca_suppress_init(d, "SCA_STANDARD_EXTRA_SUPPRESS",
                                  d.expand("${STAGING_DATADIR_NATIVE}/standard-${SCA_MODE}-suppress"))
    _findings = []

    if os.path.exists(sca_raw_result_file(d, "standard")):
        with open(sca_raw_result_file(d, "standard"), "r") as f:
            content = f.read()
            for m in re.finditer(pattern, content, re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            File=m.group("file"),
                                            BuildPath=buildpath,
                                            Column=m.group("col"),
                                            Tool="standard",
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID=m.group("id"),
                                            Severity="warning")
                    if g.ID == "null":
                        # this is a parser error, which is catched by
                        # the pattern below
                        continue
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings += sca_backtrack_findings(d, g)
                except Exception as exp:
                    sca_log_note(d, str(exp))
            for m in re.finditer(pattern_parser, content, re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            File=m.group("file").strip(),
                                            BuildPath=buildpath,
                                            Column=m.group("col"),
                                            Tool="standard",
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID="parser_error",
                                            Severity="error")
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings += sca_backtrack_findings(d, g)
                except Exception as exp:
                    sca_log_note(d, str(exp))

    sca_add_model_class_list(d, _findings)
    return sca_save_model_to_string(d)

python do_sca_standard_core() {
    import os
    import subprocess

    os.environ["HOME"] = d.getVar("T")
    _args = ["standard"]
    _args += ["--verbose"]

    _files = get_files_by_extention(d, d.getVar("SCA_SOURCES_DIR"), d.getVar("SCA_STANDARD_FILE_FILTER"), \
                                    sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA")))

    cmd_output = exec_wrap_check_output(d, _args, _files)

    with open(sca_raw_result_file(d, "standard"), "w") as o:
        o.write(cmd_output)
}

python do_sca_standard_core_report() {
    import os
    ## Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/standard.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_standard(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "standard", get_fatal_entries(d, "SCA_STANDARD_EXTRA_FATAL",
                       d.expand("${STAGING_DATADIR_NATIVE}/standard-${SCA_MODE}-fatal")))
}
