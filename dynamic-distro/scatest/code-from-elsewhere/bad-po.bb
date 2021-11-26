SUMMARY = "Bad po test files (based on msgcheck tests)"
HOMEPAGE = "https://github.com/flashcode/msgcheck"

DEFAULT_PREFERENCE = "-1"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=c678957b0c8e964aa6c70fd77641a71e"

SRC_URI = "git://github.com/flashcode/msgcheck.git;branch=master;protocol=https"
SRCREV = "08eb80622a5dedf4739c3942b3c22e7ed33d3507"
S = "${WORKDIR}/git/tests"

inherit sca

SCA_ENABLED_MODULES = "bitbake dennis msgcheck"
