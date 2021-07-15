SUMMARY = "Extension for flake8 which uses pydocstyle to check docstrings"
HOMEPAGE = "https://gitlab.com/pycqa/flake8-docstrings"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a34bc20b983e1104e2d50423b224b087"

DEPENDS += "\
            ${PYTHON_PN}-flake8-native \
            ${PYTHON_PN}-pydocstyle-native \
            "
SRC_URI[md5sum] = "adcbc65177fadb659e269fa3d99ee938"
SRC_URI[sha256sum] = "3d5a31c7ec6b7367ea6506a87ec293b94a0a46c0bce2bb4975b7f1d09b6f3717"

PYPI_PACKAGE = "flake8-docstrings"

inherit pypi
inherit native
inherit setuptools3
