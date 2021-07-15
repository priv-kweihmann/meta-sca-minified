SUMMARY = "Backport of pathlib-compatible object wrapper for zip files"
HOMEPAGE = "https://github.com/jaraco/zipp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a7126e068206290f3fe9f8d6c713ea6"

DEPENDS += "\
            ${PYTHON_PN}-setuptools-scm-native \
            ${PYTHON_PN}-toml-native \
            "

PYPI_PACKAGE = "zipp"

SRC_URI[md5sum] = "199da7385f080ec45da6c1942e2b5996"
SRC_URI[sha256sum] = "c599e4d75c98f6798c509911d08a22e6c021d074469042177c8c86fb92eefd96"

inherit pypi
inherit native
inherit setuptools3
