SUMMARY = "Phan is a static analyzer for PHP"
HOMEPAGE = "https://github.com/phan/phan"

DEFAULT_PREFERENCE = "-1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fbdcd3d0ec03d3c2e54b19846ad2b027"

DEPENDS += "php-ast-native"

SRC_URI = "git://github.com/phan/phan.git;protocol=https;branch=v5 \
           file://phan.sca.description"
SRCREV = "42ee10d1d456d4c26b72035a8051487c864d712b"
PHP_EXTENSION += "ast"
PHPCOMPOSER_PKGS_NAME = "phan/phan:${PV}"

S = "${WORKDIR}/git"

inherit phpcomposer
inherit php-ext
inherit sca-description
inherit native

do_compile:prepend() {
    rm -f ${S}/composer.json ${S}/composer.lock
}

do_install:append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/phan.sca.description ${D}${datadir}/
}

FILES:${PN} = "${bindir} ${datadir}"
