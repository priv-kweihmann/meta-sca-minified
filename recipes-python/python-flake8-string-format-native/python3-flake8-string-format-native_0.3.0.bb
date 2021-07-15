SUMMARY = "Check that indexed parameters are used in strings"
HOMEPAGE = "https://github.com/xZise/flake8-string-format"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=251fb17d242c0c87600c78fa66192abf"

DEPENDS += "${PYTHON_PN}-flake8-native"

SRC_URI = "git://github.com/xZise/flake8-string-format.git;protocol=https"
SRCREV = "356b9109f1e2ce6ae9f8179a52f1f909f99a9eb8"
S = "${WORKDIR}/git"

inherit native
inherit setuptools3
