SUMMARY = "Simple Python style checker in one Python file"
HOMEPAGE = "https://github.com/PyCQA/pycodestyle"

LICENSE = "EXPAT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS += "\
            ${PYTHON_PN}-mccabe-native \
            ${PYTHON_PN}-pycodestyle-native \
            ${PYTHON_PN}-pydocstyle-native \
            ${PYTHON_PN}-pyflakes-native \
            "

PYPI_PACKAGE = "pylama"

SRC_URI[md5sum] = "d25f86fd0cc66a03c76b96dfcca360c3"
SRC_URI[sha256sum] = "9bae53ef9c1a431371d6a8dca406816a60d547147b60a4934721898f553b7d8f"

inherit pypi
inherit native
inherit setuptools3

do_install_append() {
    ## This otherwise collides with other modules
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/tests/
}
