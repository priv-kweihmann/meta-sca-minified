SUMMARY = "Static analyzer for C/C++ based on the theory of Abstract Interpretation."
HOMEPAGE = "https://github.com/nasa-sw-vnv/ikos"

LICENSE = "NASA"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8e59a2e7eca93bf552009706554bb526"

DEPENDS += "\
            boost-native \
            clang-native \
            gmp-native \
            python3-native \
            sqlite3-native \
            tbb-native \
            "

SRC_URI = "git://github.com/NASA-SW-VnV/ikos.git;protocol=https \
           file://ikos.sca.description"
SRC_URI += "file://Upgrade-to-LLVM-10.patch"
SRCREV = "72c70c79fdef482b78b131218d16c6c7aa82edd8"

S = "${WORKDIR}/git"

inherit native
inherit cmake
inherit sca-description

EXTRA_OECMAKE += "\
                  -DBUILD_SHARED_LIBS=On \
                  -DIKOS_LINK_LLVM_DYLIB=On \
                 "

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/ikos.sca.description ${D}${datadir}
}

FILES_${PN} += "${datadir}"
