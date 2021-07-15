SUMMARY = "A really bad package"
LICENSE = "CLOSED"

SRC_URI += "\
            http://www.magicermine.com/demos/curl/curl/curl-7.30.0.ermine.tar.bz2;subdir=source \
            file://test1/LICENSE;subdir=source \
            file://test1/LICENSE.gpl2;subdir=source \
            file://test2/LICENSE;subdir=source \
            file://test1/test-1.sh;subdir=source \
            file://test2/test-2.sh;subdir=source \
            file://simple-hello-world.c;subdir=source \
           "
SRC_URI[md5sum] = "4b6fb79adc54152a9e5dc6aa3222b306"
SRC_URI[sha256sum] = "9b6a127173cfdca7db0b95ceef2a7946ad592ec23e193dc65ac3d302edc21264"

S = "${WORKDIR}/source"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${bindir}
    install -m 0477 ${S}/test1/test-1.sh ${D}${bindir}

    install -d ${D}/opt/somestrangepath
    install -m 0777 ${S}/test2/test-2.sh ${D}/opt/somestrangepath/magic.sh

    install -d ${D}${datadir}/myhiddensources
    install -m 0644 ${S}/simple-hello-world.c ${D}${datadir}/myhiddensources

    install -m 0455 ${S}/curl-7.30.0.ermine/curl.ermine ${D}${bindir}/curl.ermine
}

FILES_${PN} += "${datadir}"
FILES_${PN}-dev += "/opt/somestrangepath"

INSANE_SKIP_${PN} += "already-stripped arch"

inherit sca

SCA_ENABLED_MODULES = "pkgqaenc licensecheck"
