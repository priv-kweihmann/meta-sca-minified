SUMMARY = "Package diff"
HOMEPAGE = "https://github.com/pkg/diff"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=d0250703509998b3201f5dd7f26e8c35"

SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main"
SRCREV = "20ebb0f2a09e612109b224b32f79370409108bcc"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "github.com/pkg/diff"

export GO111MODULE = "auto"

inherit go
inherit native
