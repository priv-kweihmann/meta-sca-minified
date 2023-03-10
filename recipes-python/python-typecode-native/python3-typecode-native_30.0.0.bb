SUMMARY = "typecode provides file type detection functionality to ScanCode toolkit"
HOMEPAGE = "https://github.com/nexB/typecode"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://apache-2.0.LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS += "\
             python3-attrs-native \
             python3-binaryornot-native \
             python3-commoncode-native \
             python3-pdfminer-six-native \
             python3-plugincode-native \
             python3-setuptools-scm-native \
             python3-six-native \
             python3-toml-native \
             python3-typecode-libmagic-system-provided-native \
             python3-wheel-native \
            "

PYPI_PACKAGE = "typecode"

SRC_URI[md5sum] = "fa8ff162e21720d990885ac1755f2651"
SRC_URI[sha256sum] = "a5118b53fc73410a8364c22caead45cbb560188a458e71eda663bec8beede20f"

SRC_URI:append = " file://0001-setup.cfg-fix-invalid-version-spec.patch"

inherit pypi
inherit setuptools3
inherit native

