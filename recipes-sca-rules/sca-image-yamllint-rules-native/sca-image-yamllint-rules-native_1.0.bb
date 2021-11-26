SUMMARY = "SCA ruleset for yamllint at images"
DESCRIPTION = "Rules to configure how yamllint is affecting the build"

DEFAULT_PREFERENCE = "-1"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${SCA_LAYERDIR}/LICENSE;md5=a4a2bbea1db029f21b3a328c7a059172"

SRC_URI = "file://suppress \
           file://fatal"

inherit native

do_install() {
    install -d "${D}${datadir}"
    install "${WORKDIR}/suppress" "${D}${datadir}/yamllint-image-suppress"
    install "${WORKDIR}/fatal" "${D}${datadir}/yamllint-image-fatal"
}

FILES:${PN} = "${datadir}"
