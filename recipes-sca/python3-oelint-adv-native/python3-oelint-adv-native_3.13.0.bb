SUMMARY = "Advanced oelint"
DESCRIPTION = "Based on the OpenEmbedded Styleguide and work done by oe-stylize-tool\n \
               this module offers a (nearly) complete linter for bitbake-recipes."
HOMEPAGE = "https://github.com/priv-kweihmann/oelint-adv"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e926c89aceef6c1a4247d5df08f94533"

DEPENDS += "\
            python3-anytree-native \
            python3-colorama-native \
            python3-oelint-parser-native \
            python3-urllib3-native \
            "

SRC_URI[sha256sum] = "11be39400a30de0d4e0f3a160f862c0cef399fd3cd9e17c2015cfccfc9eb1ada"

PYPI_PACKAGE = "oelint_adv"
UPSTREAM_CHECK_REGEX ?= "/oelint-adv/(?P<pver>(\d+[\.\-_]*)+)"

inherit sca-description
inherit setuptools3
inherit pypi
inherit native

SCA_TOOL_DESCRIPTION = "oelint"
