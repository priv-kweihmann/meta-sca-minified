SUMMARY = "Tool for checking elf-symbols against rules"
HOMEPAGE = "https://github.com/priv-kweihmann/pysymbolcheck"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "BSD-2-Clause & PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39f57e3d40a441f0656539ac3aa225a5"

DEPENDS += "\
            python3-jsonmerge-native \
            python3-native \
            python3-pyelftools-native \
            "

SRC_URI = "git://github.com/priv-kweihmann/pysymbolcheck.git;protocol=https;branch=master"
SRCREV = "b79ef345d8409cb470ae29a24f110bb73d6afb41"
S = "${WORKDIR}/git"

inherit sca-description
inherit setuptools3
inherit native

SCA_TOOL_DESCRIPTION = "pysymcheck"
PIP_INSTALL_PACKAGE = "pysymbolcheck"

do_install:append() {
    install -d ${D}${datadir}/pysymbolcheck
    install ${WORKDIR}/git/basic_rules.json ${D}${datadir}/pysymbolcheck
}

FILES:${PN} += "${datadir}"

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-python3 \
    nativesdk-python3-jsonmerge \
    nativesdk-python3-pyelftools \
"
