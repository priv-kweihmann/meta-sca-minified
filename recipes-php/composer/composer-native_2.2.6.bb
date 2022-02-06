SUMMARY = "Dependency Manager for PHP"
HOMEPAGE = "https://github.com/composer/composer"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db4ebb1057458d039cb8f6edcc5d756e"

DEPENDS += "php-native"

SRC_URI = "git://github.com/composer/getcomposer.org.git;protocol=https;branch=main"
SRCREV = "2417ac77de78cec5fd3e5eb55879c54b8c533812"

UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git"

inherit sca-nonet-sanity
inherit native

do_install() {
    export COMPOSER_HOME=${D}${datadir}/composer
    install -d ${D}${bindir}
    php ${S}/web/installer --version=${PV} --install-dir=${D}${bindir}
}

FILES:${PN} += "${bindir} ${datadir}"
