SUMMARY = "SCA commenting bot"
HOMEPAGE = "https://github.com/priv-kweihmann/scabot"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

DEPENDS += "\
    python3-github3-py-native \
    python3-python-gitlab-native \
    python3-tenacity-native \
    python3-unidiff-native \
"

SRC_URI = "git://github.com/priv-kweihmann/scabot.git;branch=master;protocol=https"
SRCREV = "c7874f92bf545b7ce249485468b0c0ccd5a7d2db"

S = "${WORKDIR}/git"

inherit setuptools3
inherit native

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-python3-github3-py \
    nativesdk-python3-python-gitlab \
    nativesdk-python3-tenacity \
    nativesdk-python3-unidiff \
"
