SUMMARY = "Linux enumeration tool for pentesting and CTFs with verbosity levels"
HOMEPAGE = "https://github.com/diego-treitos/linux-smart-enumeration"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/diego-treitos/linux-smart-enumeration.git;branch=master;protocol=https"
SRCREV = "9e07dbe03d8372c166d3af037a306e4f9100b784"

S = "${WORKDIR}/git"

inherit sca-description

SCA_TOOL_DESCRIPTION = "lse"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/lse.sh ${D}${bindir}/lse.sh
}

FILES:${PN} = "${bindir}"

# We don't really care about debug package for this one
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BBCLASSEXTEND = "native"
