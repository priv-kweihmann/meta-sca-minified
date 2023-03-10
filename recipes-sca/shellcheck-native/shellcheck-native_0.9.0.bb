SUMMARY = "Static code analyzer for shell-scripts"
HOMEPAGE = "https://www.shellcheck.net/"
BUGTRACKER = "https://github.com/koalaman/shellcheck/issues"

COMPATIBLE_HOST = "^x86_64-linux|^aarch64-linux|^arm-linux|.*x86-64|.*aarch64|.*arm"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1ebbd3e34237af26da5dc08a4e440464"

DEPENDS += "\
    dpkg-native \
    gmp-native \
    libffi-native \
"

SHELLCHECK_MANUAL_ARCH ?= ""

def shellcheck_arch_map(d):
    _arch = d.getVar("SHELLCHECK_MANUAL_ARCH") or d.getVar("BUILD_ARCH")
    if _arch == "x86_64":
        return "x86_64"
    elif _arch == "aarch64":
        return "aarch64"
    elif _arch == "arm":
        return "armv6hf"

SRC_URI = "https://github.com/koalaman/shellcheck/releases/download/v${PV}/shellcheck-v${PV}.linux.${@shellcheck_arch_map(d)}.tar.xz;name=blob_${@shellcheck_arch_map(d)}"

SRC_URI[blob_x86_64.sha256sum] = "700324c6dd0ebea0117591c6cc9d7350d9c7c5c287acbad7630fa17b1d4d9e2f"
SRC_URI[blob_aarch64.sha256sum] = "179c579ef3481317d130adebede74a34dbbc2df961a70916dd4039ebf0735fae"
SRC_URI[blob_armv6hf.sha256sum] = "03deed9ded9dd66434ccf9649815bcde7d275d6c9f6dcf665b83391673512c75"

S = "${WORKDIR}/shellcheck-v${PV}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

inherit sca-description
inherit native

SCA_TOOL_DESCRIPTION = "shellcheck"

WRAPPER_LIBPATH:class-native = "${STAGING_LIBDIR_NATIVE}"
WRAPPER_LIBPATH:class-nativesdk = "${libdir}"

do_install() {
    install -d ${D}${bindir}
    cp -R ${S}/shellcheck ${D}${bindir}

    # Add a wrapper script to fix the linking issues
    cat << EOF > ${D}${bindir}/shellcheck-wrapper
#!/bin/sh

if [ ! -e "${WRAPPER_LIBPATH}/libffi.so.7" -a -e "${WRAPPER_LIBPATH}/libffi.so.6" ]; then
   ln -s ${WRAPPER_LIBPATH}/libffi.so.6 ${WRAPPER_LIBPATH}/libffi.so.7
fi

LD_LIBRARY_PATH=${WRAPPER_LIBPATH} shellcheck \$@
EOF
    chmod 0755 ${D}${bindir}/shellcheck-wrapper
}

FILES:${PN} = "${bindir}"

UPSTREAM_CHECK_URI = "https://github.com/koalaman/shellcheck/tags"
UPSTREAM_CHECK_REGEX = "releases/tag/v(?P<pver>\d+\.\d+\.\d+)"

## We know it's already stripped - because we're using prebuild things
INSANE_SKIP:${PN} += "already-stripped arch"

RDEPENDS:${PN}:class-nativesdk += "\
    nativesdk-dpkg \
    nativesdk-gmp \
    nativesdk-libffi \
"
