## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-mypy-core
inherit sca-global

inherit python3-dir

## Add ids to suppress on a recipe level
SCA_MYPY_EXTRA_SUPPRESS ?= ""
## Add ids to lead to a fatal on a recipe level
SCA_MYPY_EXTRA_FATAL ?= ""

SCA_DEPLOY_TASK = "do_sca_deploy_mypy_image"

python do_sca_deploy_mypy_image() {
    sca_conv_deploy(d, "mypy")
}

do_sca_mypy_core[doc] = "Lint python files wtth mypy in image"
do_sca_deploy_mypy_image[doc] = "Deploy results of do_sca_mypy_core"
do_sca_mypy_core_report[doc] = "Report findings from do_sca_mypy_core"
addtask do_sca_mypy_core before do_image_complete after do_image
addtask do_sca_mypy_core_report after do_sca_mypy_core
addtask do_sca_deploy_mypy_image before do_image_complete after do_sca_mypy_core_report

DEPENDS += "sca-image-mypy-rules-native"
