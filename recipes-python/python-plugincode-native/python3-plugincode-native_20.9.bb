SUMMARY = "Query metadatdata from sdists / bdists / installed packages"
HOMEPAGE = "https://bazaar.launchpad.net/~tseaver/pkginfo/trunk/files"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://apache-2.0.LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS += "\
            python3-setuptools-scm-native \
            python3-toml-native \
            python3-wheel-native \
           "

PYPI_PACKAGE = "plugincode"

SRC_URI[md5sum] = "134fa8545834ff35e6f84fbc89b9805d"
SRC_URI[sha256sum] = "dd7ac8705687f79d94d8b3834a08f314b1601a3526300239f38ebd967c9db5c1"

inherit pypi
inherit native
inherit setuptools3
