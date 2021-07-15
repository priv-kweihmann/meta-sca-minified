## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-slick-core
inherit sca-global

SCA_DEPLOY_TASK = "do_sca_deploy_slick_image"

python do_sca_deploy_slick_image() {
    sca_conv_deploy(d, "slick")
}

do_sca_slick_core[doc] = "Lint shell script with slick in image"
do_sca_deploy_slick_image[doc] = "Deploy results of do_sca_slick_core"
do_sca_slick_core_report[doc] = "Report findings from do_sca_slick_core"
addtask do_sca_slick_core before do_image_complete after do_image
addtask do_sca_slick_core_report after do_sca_slick_core
addtask do_sca_deploy_slick_image before do_image_complete after do_sca_slick_core_report

DEPENDS += "sca-image-slick-rules-native"
