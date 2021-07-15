SUMMARY = "SCA description for govet"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${SCA_LAYERDIR}/LICENSE;md5=a4a2bbea1db029f21b3a328c7a059172"

DEPENDS += "go-native"

SRC_URI = "file://govet.sca.description"

inherit native
inherit sca-description

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}
    install ${WORKDIR}/govet.sca.description ${D}${datadir}/
}

FILES_${PN} = "${datadir}"
