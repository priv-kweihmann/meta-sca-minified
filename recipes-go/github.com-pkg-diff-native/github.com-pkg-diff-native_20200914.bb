SUMMARY = "Package diff"
HOMEPAGE = "https://github.com/pkg/diff"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=d0250703509998b3201f5dd7f26e8c35"

SRC_URI = "git://${GO_IMPORT};protocol=https"
SRCREV = "5b29258ca4f7ff576678c8baa990885f14449c4f"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "github.com/pkg/diff"

inherit gonative
inherit native
