## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

SCA_SYSTEMDLINT_EXTRA_SUPPRESS ?= ""
SCA_SYSTEMDLINT_EXTRA_FATAL ?= ""
SCA_SYSTEMDLINT_SYSTEMD_VERSION ?= ""
SCA_SYSTEMDLINT_FILES ?= ".automount .conf .link .mount .network .path .service .slice .socket .swap .target .timer"
SCA_SYSTEMDLINT_PATHS ?= "${SCA_SOURCES_DIR}/${sysconfdir}/systemd \
                          ${SCA_SOURCES_DIR}/${libdir}/systemd \
                          ${SCA_SOURCES_DIR}/run/systemd"

SCA_RAW_RESULT_FILE[systemdlint] = "txt"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-license-filter
inherit sca-suppress

inherit python3native

DEPENDS += "python3-systemdlint-native"

def do_sca_conv_systemdlint(d):
    import os
    import re
    
    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")

    pattern = r"^(?P<file>.*):(?P<line>\d+):(?P<severity>\w+)\s\[(?P<id>.*)\]\s+-\s+(?P<msg>.*)"

    severity_map = {
        "error" : "error",
        "warning" : "warning",
        "info": "info"
    }

    _suppress = sca_suppress_init(d)
    _findings = []

    if os.path.exists(sca_raw_result_file(d, "systemdlint")):
        with open(sca_raw_result_file(d, "systemdlint"), "r") as f:
            for m in re.finditer(pattern, f.read(), re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            Tool="systemdlint",
                                            BuildPath=buildpath,
                                            File=m.group("file"),
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID=m.group("id"),
                                            Severity=severity_map[m.group("severity")])
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

python do_sca_systemdlint() {
    import os
    import subprocess

    d.setVar("SCA_EXTRA_SUPPRESS", d.getVar("SCA_SYSTEMDLINT_EXTRA_SUPPRESS"))
    d.setVar("SCA_EXTRA_FATAL", d.getVar("SCA_SYSTEMDLINT_EXTRA_FATAL"))
    d.setVar("SCA_SUPRESS_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "systemdlint-{}-suppress".format(d.getVar("SCA_MODE"))))
    d.setVar("SCA_FATAL_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "systemdlint-{}-fatal".format(d.getVar("SCA_MODE"))))

    _args = ['systemdlint']
    _args += ["--rootpath={}".format(d.getVar("SCA_SOURCES_DIR"))]
    _args += ["--output={}".format(sca_raw_result_file(d, "systemdlint"))]
    if d.getVar("SCA_SYSTEMDLINT_SYSTEMD_VERSION"):
        _args += ["--sversion={}".format(d.getVar("SCA_SYSTEMDLINT_SYSTEMD_VERSION"))]
    _files = []
    for path in clean_split(d, "SCA_SYSTEMDLINT_PATHS"):
        _files += get_files_by_extention(d, path,  d.getVar("SCA_SYSTEMDLINT_FILES"), \
                                            sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA")))

    with open(sca_raw_result_file(d, "systemdlint"), "w") as o:
        o.write("")

    if any(_files):
        _args += _files
        try:
            subprocess.check_output(_args, universal_newlines=True, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            bb.warn(str(e))

    ## Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/systemdlint.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_systemdlint(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "systemdlint", get_fatal_entries(d))
}

SCA_DEPLOY_TASK = "do_sca_deploy_systemdlint_image"

python do_sca_deploy_systemdlint_image() {
    sca_conv_deploy(d, "systemdlint")
}

do_sca_systemdlint[doc] = "Lint systemd unit files in image"
do_sca_deploy_systemdlint_image[doc] = "Deploy results of do_sca_systemdlint"
addtask do_sca_systemdlint before do_image_complete after do_image
addtask do_sca_deploy_systemdlint_image before do_image_complete after do_sca_systemdlint

DEPENDS += "sca-image-systemdlint-rules-native"
