SUMMARY = "Bandit is a tool designed to find common security issues in Python code"
HOMEPAGE = "https://github.com/PyCQA/bandit"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

DEPENDS += "\
            python3-gitpython-native \
            python3-native \
            python3-pbr-native \
            python3-pyyaml-native \
            python3-rich-native \
            python3-six-native \
            python3-stevedore-native \
           "

SRC_URI[sha256sum] = "bdfc739baa03b880c2d15d0431b31c658ffc348e907fe197e54e0389dd59e11e"

PYPI_PACKAGE = "bandit"

inherit pypi
inherit sca-description
inherit setuptools3
inherit native

SCA_TOOL_DESCRIPTION = "bandit"

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-python3-core \
    nativesdk-python3-datetime \
    nativesdk-python3-gitpython \
    nativesdk-python3-io \
    nativesdk-python3-logging \
    nativesdk-python3-pbr \
    nativesdk-python3-pyyaml \
    nativesdk-python3-rich \
    nativesdk-python3-six \
    nativesdk-python3-stevedore \
"
