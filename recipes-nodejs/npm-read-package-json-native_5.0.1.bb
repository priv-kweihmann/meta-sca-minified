SUMMARY = "NPM: read-package-json"
DESCRIPTION = "The thing npm uses to read package.json files with semantics and defaults and validation"
HOMEPAGE = "https://github.com/npm/read-package-json#readme"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6392dc4522c189c9616b2ec1ab763bec"

DEPENDS = "npm-glob-native \
           npm-json-parse-even-better-errors-native \
           npm-normalize-package-data-native \
           npm-npm-normalize-package-bin-native"

SRC_URI = "https://registry.npmjs.org/read-package-json/-/read-package-json-5.0.1.tgz"
SRC_URI[md5sum] = "1397b8fe91af3021bc3188280f1ecc2b"
SRC_URI[sha256sum] = "62f9c9f37a68cac587a208062bd4f88ec2308c1e7985320a6d56ca92bfe7915d"

NPM_PKGNAME = "read-package-json"

inherit npmhelper
inherit native
