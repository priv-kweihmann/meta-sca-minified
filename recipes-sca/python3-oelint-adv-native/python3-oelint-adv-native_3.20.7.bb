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

SRC_URI[sha256sum] = "33e8ef5264e12425a92057a7e9ccbace0bfc29a8505c9dabdce8af219d1fc9a9"

PYPI_PACKAGE = "oelint_adv"
UPSTREAM_CHECK_REGEX ?= "/oelint-adv/(?P<pver>(\d+[\.\-_]*)+)"

inherit sca-description
inherit setuptools3
inherit pypi
inherit native

SCA_TOOL_DESCRIPTION = "oelint"
