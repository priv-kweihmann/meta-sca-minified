## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

SCA_RECONBF_EXTRA_SUPPRESS ?= ""
SCA_RECONBF_EXTRA_FATAL ?= ""
SCA_RECONBF_CONFIG ?= "${datadir}/etc/reconbf/hos.cfg"

SCA_RAW_RESULT_FILE[reconbf] = "json"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-license-filter
inherit sca-crossemu
inherit sca-suppress

DEPENDS += "reconbf reconbf-native"

def do_sca_conv_reconbf(d):
    import os
    import json
    import hashlib
    
    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")

    items = []

    __excludes = sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA"))
    _suppress = sca_suppress_init(d)
    _findings = []

    if os.path.exists(sca_raw_result_file(d, "reconbf")):
        j = []
        try:
            with open(sca_raw_result_file(d, "reconbf")) as i:
                j = json.load(i)
        except:
            j = []
        for item in j:
            try:
                if isinstance(item["result"], str):
                    item = {"result": [item], "name": item["name"]}
                _name = item["name"]
                for sub in item["result"]:
                    if str(sub["result"]) == "FAIL":
                        if sub["name"] != _name:
                            _name += "." + hashlib.md5(str.encode(sub["name"])).hexdigest()
                        _msg = "Failed {}".format(sub["name"])
                        if "notes" in sub and sub["notes"]:
                            _msg += ": " + sub["notes"]
                        g = sca_get_model_class(d,
                                                PackageName=package_name,
                                                Tool="reconbf",
                                                BuildPath=sca_get_layer_path_for_file(d, d.getVar("FILE")),
                                                File=d.getVar("FILE"),
                                                Message=_msg,
                                                ID=_name,
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

fakeroot python do_sca_reconbf() {
    import os
    import subprocess

    d.setVar("SCA_EXTRA_SUPPRESS", d.getVar("SCA_RECONBF_EXTRA_SUPPRESS"))
    d.setVar("SCA_EXTRA_FATAL", d.getVar("SCA_RECONBF_EXTRA_FATAL"))
    d.setVar("SCA_SUPRESS_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "reconbf-{}-suppress".format(d.getVar("SCA_MODE"))))
    d.setVar("SCA_FATAL_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "reconbf-{}-fatal".format(d.getVar("SCA_MODE"))))

    _args = ["/bin/sh", "-c", "mkdir -p /tmp;python3 -m reconbf -c {} -rt json -dm all -rf /tmp/reconbf.result >/dev/null 2>&1;cat /tmp/reconbf.result".format(d.getVar("SCA_RECONBF_CONFIG"))]

    cmd_output, _ = sca_crossemu(d, _args, ["reconbf"], "reconbf", ";")

    if not isinstance(cmd_output, str):
        cmd_output = cmd_output.decode("utf-8")
    with open(sca_raw_result_file(d, "reconbf"), "w") as o:
        if not cmd_output.startswith("["):
            cmd_output = cmd_output[cmd_output.find("["):]
        o.write(cmd_output)

    # Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/reconbf.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_reconbf(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "reconbf", get_fatal_entries(d))
}

SCA_DEPLOY_TASK = "do_sca_deploy_reconbf_image"

python do_sca_deploy_reconbf_image() {
    sca_conv_deploy(d, "reconbf")
}

do_sca_reconbf[doc] = "Run reconbf audit on image"
do_sca_deploy_reconbf_image[doc] = "Deploy results of do_sca_reconbf"
addtask do_sca_reconbf before do_image_complete after do_image
addtask do_sca_deploy_reconbf_image before do_image_complete after do_sca_reconbf

DEPENDS += "sca-image-reconbf-rules-native"
