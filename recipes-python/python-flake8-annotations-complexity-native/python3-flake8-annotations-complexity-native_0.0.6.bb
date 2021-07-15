SUMMARY = "flake8 plugin to validate annotations complexity"
HOMEPAGE = "https://github.com/best-doctor/flake8-annotations-complexity"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=352fd8fef192c98968c70971a80f959c"

SRC_URI = "git://github.com/best-doctor/flake8-annotations-complexity.git;protocol=https"
SRCREV = "fe032cd60d42724660ce9129f6bc5d4114a31a2f"

UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git"

inherit setuptools3
inherit native
