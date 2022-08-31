SUMMARY = "Package requirements checker, plugin for flake8"
HOMEPAGE = "https://github.com/Arkq/flake8-requirements"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8e37d68747f8cd3c0ec28d12f1492271"

DEPENDS += "\
    nativesdk-python3-flake8 \
    python3-pytest-runner-native \
    python3-toml-native \
"

SRC_URI = "git://github.com/Arkq/flake8-requirements.git;branch=master;protocol=https"
SRCREV = "54c3f55f35c5ab1db05ddcb90708c0cd2d0941db"

S = "${WORKDIR}/git"

inherit setuptools3
inherit nativesdk

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-python3-core \
    nativesdk-python3-flake8 \
    nativesdk-python3-toml \
"
