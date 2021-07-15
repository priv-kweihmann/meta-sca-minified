SUMMARY = "NPM: @babel/helper-replace-supers"
DESCRIPTION = "Helper function to replace supers"
HOMEPAGE = "https://babel.dev/docs/en/next/babel-helper-replace-supers"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1d0cd283a346e919abb3beeb018279d"

DEPENDS = "npm-babel-helper-member-expression-to-functions-native \
           npm-babel-helper-optimise-call-expression-native \
           npm-babel-traverse-native \
           npm-babel-types-native"

SRC_URI = "https://registry.npmjs.org/@babel/helper-replace-supers/-/helper-replace-supers-7.14.5.tgz"
SRC_URI[md5sum] = "1c03b048a59efa3e12461485edbd103b"
SRC_URI[sha256sum] = "d1c7fc7e817eb0eea4602849e82e1325612a3c916b10261b0077a7ba28671c7f"

NPM_PKGNAME = "@babel/helper-replace-supers"

inherit npmhelper
inherit native
