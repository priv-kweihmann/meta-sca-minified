SUMMARY = "Systemd Linter"
HOMEPAGE = "https://github.com/priv-kweihmann/systemdlint"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=1e0b805e34c99594e846fa46c20d8b9b"

DEPENDS += "\
            ${PYTHON_PN}-anytree-native \
            ${PYTHON_PN}-systemdunitparser-native \
           "

SRC_URI = "git://github.com/priv-kweihmann/systemdlint.git;protocol=https;branch=master \
           file://systemdlint.sca.description"
SRCREV = "ad4610c602a62eb90641be05e1436af9fe83d63c"
S = "${WORKDIR}/git/systemdlint"

inherit native
inherit sca-description
inherit setuptools3

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/systemdlint.sca.description ${D}${datadir}
}

FILES_${PN} += "${datadir}"
