SUMMARY = "Flake8 extension to validate (lack of) logging format strings"
HOMEPAGE = "https://github.com/globality-corp/flake8-logging-format"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/globality-corp/flake8-logging-format.git;branch=master;protocol=https"
SRCREV = "26f98153482a8c61b47be7a41681cf413ec4cc8a"

S = "${WORKDIR}/git"

inherit setuptools3
inherit nativesdk

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-python3-core \
    nativesdk-python3-flake8 \
"
