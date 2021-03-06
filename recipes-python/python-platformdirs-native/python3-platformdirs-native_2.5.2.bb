SUMMARY = "A small Python module for determining appropriate platform-specific dirs"
HOMEPAGE = "https://github.com/platformdirs/platformdirs"

DEPENDS += " \
    python3-hatch-vcs-native \
    python3-setuptools-scm-native \
    python3-toml-native \
"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=282c970bb844954c8535dd6e9733db7f"

PYPI_PACKAGE = "platformdirs"

SRC_URI[md5sum] = "2301a8a29c3082a49ee293073d893887"
SRC_URI[sha256sum] = "58c8abb07dcb441e6ee4b11d8df0ac856038f944ab98b7be6b27b2a3c7feef19"

inherit pypi
inherit python_hatchling
inherit native
