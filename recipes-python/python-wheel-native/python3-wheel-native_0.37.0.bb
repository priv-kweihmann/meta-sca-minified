SUMMARY = "The official binary distribution format for Python"
HOMEPAGE = "https://github.com/pypa/wheel"

DEFAULT_PREFERENCE = "-1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=9d66b41bc2a080e7174acc5dffecd752"

SRC_URI:append = "  https://raw.githubusercontent.com/pypa/wheel/${PV}/LICENSE.txt;name=license"
SRC_URI[license.sha256sum] = "cca9e20c6af1fcfbf69408f377769286cbeebcded336100c9b4a3f35fbe635e4"

PYPI_PACKAGE = "wheel"

SRC_URI[md5sum] = "79f55b898e6f274f5586bbde39f6fe8e"
SRC_URI[sha256sum] = "e2ef7239991699e3355d54f8e968a21bb940a1dbf34a4d226741e64462516fad"

inherit pypi
inherit setuptools3
inherit native
