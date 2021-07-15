SUMMARY = "NPM: buffer"
DESCRIPTION = "Node.js Buffer API, for the browser"
HOMEPAGE = "https://github.com/feross/buffer"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e49e579dbcc02cf1f699deec85fd96f0"

DEPENDS = "npm-base64-js-native \
           npm-ieee754-native"

SRC_URI = "https://registry.npmjs.org/buffer/-/buffer-5.6.0.tgz"
SRC_URI[md5sum] = "bbee025762658f2c08356b905c91f699"
SRC_URI[sha256sum] = "b9ec3ea5264dc312d869ff76f68bc770cab6df117de95e7ba7e196c4220e2505"

NPM_PKGNAME = "buffer"

inherit npmhelper
inherit native
