SUMMARY = "NPM: @secretlint/secretlint-rule-gcp"
DESCRIPTION = "A secretlint rule for GCP."
HOMEPAGE = "https://github.com/secretlint/secretlint/tree/master/packages/@secretlint/secretlint-rule-gcp/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=402802be77c922cb018a8b5099208dab"

DEPENDS = "npm-node-forge-native \
           npm-secretlint-types-native \
           npm-textlint-regexp-string-matcher-native"

SRC_URI = "https://registry.npmjs.org/@secretlint/secretlint-rule-gcp/-/secretlint-rule-gcp-4.0.0.tgz"
SRC_URI[md5sum] = "b7fb39ac0bf54eaf9174ecc55f168a93"
SRC_URI[sha256sum] = "da96379a8f3d476e9f8af38167d6c7cf7ffb6fbe00006d425efc3764c15fff9f"

NPM_PKGNAME = "@secretlint/secretlint-rule-gcp"

inherit npmhelper
inherit native
