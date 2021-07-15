SUMMARY = "NPM: cspell"
DESCRIPTION = "A Spelling Checker for Code!"
HOMEPAGE = "https://github.com/streetsidesoftware/cspell#readme"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1befbdcbeb73a7fd9b944d31f0fee64"

DEPENDS = "npm-chalk-native \
           npm-commander-native \
           npm-comment-json-native \
           npm-cspell-cspell-types-native \
           npm-cspell-glob-native \
           npm-cspell-lib-native \
           npm-fs-extra-native \
           npm-get-stdin-native \
           npm-glob-native \
           npm-strip-ansi-native"

SRC_URI = "https://registry.npmjs.org/cspell/-/cspell-5.4.1.tgz"
SRC_URI[md5sum] = "ffee5af5c5ded1f91fc6417a121aabdb"
SRC_URI[sha256sum] = "69ba6e6c1d077aa5d0b720256c609a28935b3cfcd1eea1d32254e5a8b91ea952"

NPM_PKGNAME = "cspell"

inherit npmhelper
inherit native
