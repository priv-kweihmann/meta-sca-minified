SUMMARY = "Sparse - a Semantic Parser for C"
HOMEPAGE = "https://sparse.wiki.kernel.org/index.php/Main_Page"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=69a9605316748b9e191e454efc2235b1"

SRC_URI = "git://git.kernel.org/pub/scm/devel/sparse/sparse.git;protocol=https \
           file://sparse.sca.description"
SRCREV = "8ab81af8985237432393894e814bb0ae1d7a1799"
S = "${WORKDIR}/git"

inherit native
inherit pkgconfig
inherit sca-description

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${exec_prefix}

    install -d ${D}${datadir}
    install ${WORKDIR}/sparse.sca.description ${D}${datadir}
}

FILES_${PN} = "${bindir} ${mandir} ${datadir}"
