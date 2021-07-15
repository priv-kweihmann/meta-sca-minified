SUMMARY = "Quick helpers for testing Go applications"
HOMEPAGE = "https://github.com/frankban/quicktest"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=98bdc79edcf073ecd345d44297905708"

DEPENDS += "\
            github.com-google-go-cmp-native \
            github.com-kr-pretty-native \
           "

SRC_URI = "git://${GO_IMPORT};protocol=https"
SRCREV = "e9d97c84f645deba54322b054a34121b260da479"
GO_IMPORT = "github.com/frankban/quicktest"

inherit gonative
inherit native
