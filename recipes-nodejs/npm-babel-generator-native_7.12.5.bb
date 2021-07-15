SUMMARY = "NPM: @babel/generator"
DESCRIPTION = "Turns an AST into code."
HOMEPAGE = "https://babeljs.io/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1d0cd283a346e919abb3beeb018279d"

DEPENDS = "npm-babel-types-native \
           npm-jsesc-native \
           npm-source-map-native"

SRC_URI = "https://registry.npmjs.org/@babel/generator/-/generator-7.12.5.tgz"
SRC_URI[md5sum] = "b872bd32120025c293cf1415966df8d3"
SRC_URI[sha256sum] = "afb3180ea19628e99057a859468850f013880ffd3aadb7332d553529b182a184"

NPM_PKGNAME = "@babel/generator"

inherit npmhelper
inherit native
