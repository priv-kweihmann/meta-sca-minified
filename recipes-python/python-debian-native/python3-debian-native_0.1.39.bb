SUMMARY = "Debian package related modules"
HOMEPAGE = "https://salsa.debian.org/python-debian-team/python-debian"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "\
            python3-chardet-native \
            python3-six-native \
           "

PYPI_PACKAGE = "python-debian"

SRC_URI[md5sum] = "a827ca9036d884c3e17f3db9dfeccea7"
SRC_URI[sha256sum] = "6cca96239b5981f5203216d2113fea522477628607ed0a8427e15094a792541c"

inherit pypi
inherit setuptools3
inherit native
