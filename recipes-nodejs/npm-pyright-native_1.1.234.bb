SUMMARY = "NPM: pyright"
DESCRIPTION = "Type checker for the Python language"
HOMEPAGE = "https://github.com/Microsoft/pyright#readme"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a2a1e4d251b834bf3e0ceee69ab1d4c9"

DEPENDS = ""

SRC_URI = "https://registry.npmjs.org/pyright/-/pyright-1.1.234.tgz"
SRC_URI[md5sum] = "2c17e14a9a41f299630d2527a4713a09"
SRC_URI[sha256sum] = "16915f4df07b06ea2c301c5e2c85d12e981f2213955ffcf39ac94fcc0b4f0f5c"

NPM_PKGNAME = "pyright"

inherit npmhelper
inherit native
