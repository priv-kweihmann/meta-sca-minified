SUMMARY = "Linux system security checks"
DESCRIPTION = "script to check the properties of executables (like PIE, RELRO, PaX, Canaries, ASLR, Fortify Source)"
HOMEPAGE = "https://github.com/slimm609/checksec.sh"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5bb0d06223ae3b075dbcf2247a3eb9ff"

SRC_URI = "\
    git://github.com/slimm609/checksec.sh \
    file://checksec.sca.description \
"
SRCREV = "70c94213fa5c62e6dfcf6f6c721346977112acb4"

S = "${WORKDIR}/git"

inherit sca-description

do_install:class-target() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/checksec ${D}${bindir}
}

do_install:class-native () {
    install -d ${D}/${datadir}
    install ${WORKDIR}/checksec.sca.description ${D}${datadir}
}

RDEPENDS:${PN}:class-target += "\
    bash \
    binutils \
    file \
    openssl-bin \
"

FILES:${PN} += "${bindir}"
FILES:${PN}:class-native += "${datadir}"

BBCLASSEXTEND = "native"
