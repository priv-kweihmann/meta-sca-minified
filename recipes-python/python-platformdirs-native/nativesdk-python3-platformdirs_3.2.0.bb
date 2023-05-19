SUMMARY = "A small Python module for determining appropriate platform-specific dirs"
HOMEPAGE = "https://github.com/platformdirs/platformdirs"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea4f5a41454746a9ed111e3d8723d17a"

DEPENDS += "\
    python3-hatch-vcs-native \
    python3-setuptools-scm-native \
    python3-toml-native \
"

PYPI_PACKAGE = "platformdirs"

SRC_URI[md5sum] = "8431910dc5d07366515a274acdbc8511"
SRC_URI[sha256sum] = "d5b638ca397f25f979350ff789db335903d7ea010ab28903f57b27e1b16c2b08"

inherit pypi
inherit python_hatchling
inherit nativesdk

do_compile:prepend() {
    export SETUPTOOLS_SCM_PRETEND_VERSION="${PV}"
}

FILES:${PN} += "${datadir} ${prefix}"

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-python3-core \
    nativesdk-python3-ctypes \
    nativesdk-python3-hatchling \
    nativesdk-python3-pathspec \
    nativesdk-python3-pluggy \
"
