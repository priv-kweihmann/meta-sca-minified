SUMMARY = "NPM: electron-to-chromium"
DESCRIPTION = "Provides a list of electron-to-chromium version mappings"
HOMEPAGE = "https://github.com/kilian/electron-to-chromium#readme"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c4bd4cd0c32203ba1496aa7686422491"

DEPENDS = ""

SRC_URI = "https://registry.npmjs.org/electron-to-chromium/-/electron-to-chromium-1.3.874.tgz"
SRC_URI[md5sum] = "494ef6e0c982d2c6f325d469f20ef075"
SRC_URI[sha256sum] = "feba7a8a13ceb833d68848176e3d8f9fe00c22efa60261746ab299ae7e49acc4"

NPM_PKGNAME = "electron-to-chromium"

inherit npmhelper
inherit native
