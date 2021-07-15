SUMMARY = "NPM: @babel/helper-split-export-declaration"
DESCRIPTION = ""
HOMEPAGE = "https://babel.dev/docs/en/next/babel-helper-split-export-declaration"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1d0cd283a346e919abb3beeb018279d"

DEPENDS = "npm-babel-types-native"

SRC_URI = "https://registry.npmjs.org/@babel/helper-split-export-declaration/-/helper-split-export-declaration-7.14.5.tgz"
SRC_URI[md5sum] = "67f48b94b6d7d0cdf498bfbec6f247d4"
SRC_URI[sha256sum] = "a3ca9fba2e31171a5e367886f61f0a418fd43f1174e29a48ef6f46e6d9868a36"

NPM_PKGNAME = "@babel/helper-split-export-declaration"

inherit npmhelper
inherit native
