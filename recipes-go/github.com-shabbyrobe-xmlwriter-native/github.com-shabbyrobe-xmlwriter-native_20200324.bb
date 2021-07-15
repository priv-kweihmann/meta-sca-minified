SUMMARY = "procedural XML generation based on libxml2's xmlwriter module"
DESCRIPTION = "xmlwriter is a pure-Go library providing procedural XML generation based on libxml2's xmlwriter module"
HOMEPAGE = "https://github.com/shabbyrobe/xmlwriter"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS += "golang.org-lib-native"

SRC_URI = "git://${GO_IMPORT};protocol=https"
SRCREV = "440e98cf0c87b91f0960818eed2116e33af1b43a"
GO_IMPORT = "github.com/shabbyrobe/xmlwriter"

UPSTREAM_CHECK_COMMITS = "1"

export GO111MODULE = "auto"

inherit go
inherit native

do_configure_prepend() {
    rm -rf ${S}/src/${GO_IMPORT}/tester
}