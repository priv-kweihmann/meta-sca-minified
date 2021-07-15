SUMMARY = "Easy column formatted output for golang"
HOMEPAGE = "https://github.com/ryanuber/columnize"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=2dcf22ecbbb7a43c2280f859bdb4bc76"

SRC_URI = "git://${GO_IMPORT};protocol=https"
SRCREV = "9e6335e58db3b4cfe3c3c5c881f51ffbc1091b34"
GO_IMPORT = "github.com/ryanuber/columnize"

inherit go
inherit native
