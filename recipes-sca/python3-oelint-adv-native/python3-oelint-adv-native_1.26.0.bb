SUMMARY = "Advanced oelint"
DESCRIPTION = "Based on the OpenEmbedded Styleguide and work done by oe-stylize-tool\n \
               this module offers a (nearly) complete linter for bitbake-recipes."
HOMEPAGE = "https://github.com/priv-kweihmann/oelint-adv"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e926c89aceef6c1a4247d5df08f94533"

DEPENDS += "\
            python3-anytree-native \
            python3-colorama-native \
            python3-urllib3-native \
            "

SRC_URI = "git://github.com/priv-kweihmann/oelint-adv.git;protocol=https;branch=master \
           file://oelint.sca.description"
SRCREV = "211581d7f0f558b8dfa7e8549b3fcff687a6b01b"
S = "${WORKDIR}/git"

inherit native
inherit sca-description
inherit setuptools3

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/oelint.sca.description ${D}${datadir}
}

FILES_${PN} += "${datadir}"
