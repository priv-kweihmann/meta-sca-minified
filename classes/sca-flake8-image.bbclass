## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-flake8-core
inherit sca-global

inherit python3-dir

## Add ids to suppress on a recipe level
SCA_FLAKE8_EXTRA_SUPPRESS ?= ""
## Add ids to lead to a fatal on a recipe level
SCA_FLAKE8_EXTRA_FATAL ?= ""

SCA_DEPLOY_TASK = "do_sca_deploy_flake8_image"

python do_sca_deploy_flake8_image() {
    sca_conv_deploy(d, "flake8")
}

do_sca_flake8_core[doc] = "Lint python code with flake8 in image"
do_sca_deploy_flake8_image[doc] = "Deploy results of do_sca_flake8_core"
do_sca_flake8_core_report[doc] = "Report findings from do_sca_flake8_core"
addtask do_sca_flake8_core before do_image_complete after do_image
addtask do_sca_flake8_core_report after do_sca_flake8_core
addtask do_sca_deploy_flake8_image before do_image_complete after do_sca_flake8_core_report

DEPENDS += "sca-image-flake8-rules-native"
