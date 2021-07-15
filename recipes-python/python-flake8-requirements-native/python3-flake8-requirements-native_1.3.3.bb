SUMMARY = "Package requirements checker, plugin for flake8"
HOMEPAGE = "https://github.com/Arkq/flake8-requirements"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f6a7ebcbe234d94509bad74d466632ee"

DEPENDS += "\
            ${PYTHON_PN}-flake8-native \
            ${PYTHON_PN}-toml-native \
            "

SRC_URI = "git://github.com/Arkq/flake8-requirements.git;protocol=https"
SRCREV = "a8150441d7f168a2e5a40fcb67dac79844dc849e"

S = "${WORKDIR}/git"

inherit native
inherit setuptools3
