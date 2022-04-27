SUMMARY = "NPM: cspell-lib"
DESCRIPTION = "A library of useful functions used across various cspell tools."
HOMEPAGE = "https://github.com/streetsidesoftware/cspell#readme"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1befbdcbeb73a7fd9b944d31f0fee64"

DEPENDS = "npm-clear-module-native \
           npm-comment-json-native \
           npm-configstore-native \
           npm-cosmiconfig-native \
           npm-cspell-cspell-bundled-dicts-native \
           npm-cspell-cspell-pipe-native \
           npm-cspell-cspell-types-native \
           npm-cspell-glob-native \
           npm-cspell-io-native \
           npm-cspell-trie-lib-native \
           npm-fast-equals-native \
           npm-find-up-native \
           npm-fs-extra-native \
           npm-gensequence-native \
           npm-import-fresh-native \
           npm-resolve-from-native \
           npm-resolve-global-native \
           npm-vscode-languageserver-textdocument-native \
           npm-vscode-uri-native"

SRC_URI = "https://registry.npmjs.org/cspell-lib/-/cspell-lib-5.19.7.tgz"
SRC_URI[md5sum] = "beb97e6f3a1d8906781fd5e092ae8f57"
SRC_URI[sha256sum] = "32620423233cfb0d3c8507f18d35c24597db6deba20168128830913ba7738978"

NPM_PKGNAME = "cspell-lib"

inherit npmhelper
inherit native
