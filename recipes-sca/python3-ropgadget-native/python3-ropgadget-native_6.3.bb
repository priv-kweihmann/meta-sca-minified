SUMMARY = "Find ROP exploitation pattern"
DESCRIPTION = "This tool lets you search your gadgets on your binaries to facilitate your ROP exploitation"
HOMEPAGE = "https://github.com/JonathanSalwan/ROPgadget"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE_BSD.txt;md5=e8b383a8cb935c894fbf61ff6c5d5121"

DEPENDS += "\
            ${PYTHON_PN}-capstone-native \
            ${PYTHON_PN}-native \
           "

SRC_URI = "git://github.com/JonathanSalwan/ROPgadget.git;protocol=https \
           file://ropgadget.sca.description"
SRCREV = "c29c50773ec7fb3df56396ce27fb71c3898c53ae"
S = "${WORKDIR}/git"

inherit native
inherit sca-description
inherit setuptools3

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/ropgadget.sca.description ${D}${datadir}
}

FILES_${PN} += "${datadir}"
