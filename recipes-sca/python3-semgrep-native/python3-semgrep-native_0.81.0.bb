SUMMARY = "Lightweight static analysis for many languages"
HOMEPAGE = "https://github.com/returntocorp/semgrep"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS += "\
            python3-attrs-native \
            python3-click-native \
            python3-click-option-group-native \
            python3-colorama-native \
            python3-jsonschema-native \
            python3-packaging-native \
            python3-peewee-native \
            python3-requests-native \
            python3-ruamelyaml-native \
            python3-ruamelyamlcli-native \
            python3-tqdm-native \
            python3-wcmatch-native \
            python3-wheel-native \
            semgrep-core-native \
           "

SRC_URI[md5sum] = "9401ef10616b77c1bebdbe649517ed51"
SRC_URI[sha256sum] = "85364400eb24f74591226f8729bf25461a8c57fdaec77aed4a5674a74ed44eb1"

PYPI_PACKAGE = "semgrep"

inherit pypi
inherit sca-description
inherit setuptools3
inherit native

SCA_TOOL_DESCRIPTION = "semgrep"

export SEMGREP_SKIP_BIN = "1"

do_configure:prepend() {
    # Remove the version pinning
    sed -i "s#jsonschema~=3.2.0#jsonschema>=3.2.0#g" "${S}/setup.py"
}

do_install:append() {
    # remove test files
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/tests
}
