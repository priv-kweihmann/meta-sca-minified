# This file is a modified version of files to be found at 
# https://git.openembedded.org/meta-openembedded/plain/meta-networking/recipes-support/libtalloc/
# 
# The recipe and the provided patches are licensed under the following terms
#
# Permission is hereby granted, free of charge, to any person obtaining a copy 
# of this software and associated documentation files (the "Software"), to deal 
# in the Software without restriction, including without limitation the rights 
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
# copies of the Software, and to permit persons to whom the Software is 
# furnished to do so, subject to the following conditions:

# The above copyright notice and this permission notice shall be included in 
# all copies or substantial portions of the Software.

# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
# THE SOFTWARE.

SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://talloc.samba.org"
SECTION = "libs"
LICENSE = "LGPL-3.0+ & GPL-3.0+"
LIC_FILES_CHKSUM = "file://talloc.h;beginline=3;endline=27;md5=a301712782cad6dd6d5228bfa7825249 \
                    file://pytalloc.h;beginline=1;endline=18;md5=2c498cc6f2263672483237b20f46b43d"


SRC_URI = "https://samba.org/ftp/talloc/talloc-${PV}.tar.gz \
           file://options-2.1.14.patch \
"
SRC_URI[md5sum] = "7478da02e309316231a497a9f17a980d"
SRC_URI[sha256sum] = "b185602756a628bac507fa8af8b9df92ace69d27c0add5dab93190ad7c3367ce"

inherit native

PACKAGECONFIG ??= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'acl', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xattr', 'attr', '', d)} \
"
PACKAGECONFIG[acl] = "--with-acl,--without-acl,acl"
PACKAGECONFIG[attr] = "--with-attr,--without-attr,attr"
PACKAGECONFIG[libaio] = "--with-libaio,--without-libaio,libaio"
PACKAGECONFIG[libbsd] = "--with-libbsd,--without-libbsd,libbsd"
PACKAGECONFIG[libcap] = "--with-libcap,--without-libcap,libcap"
PACKAGECONFIG[valgrind] = "--with-valgrind,--without-valgrind,valgrind"

SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'attr', '', 'file://avoid-attr-unless-wanted.patch', d)}"

S = "${WORKDIR}/talloc-${PV}"

EXTRA_OECONF += "--disable-rpath \
                 --disable-rpath-install \
                 --bundled-libraries=NONE \
                 --builtin-libraries=replace \
                 --disable-silent-rules \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix}\
                "

PACKAGES += "pytalloc pytalloc-dev"

RPROVIDES_${PN}-dbg += "pytalloc-dbg"

FILES_pytalloc = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/* \
                  ${libdir}/libpytalloc-util.so.2 \
                  ${libdir}/libpytalloc-util.so.2.1.1 \
                 "
FILES_pytalloc-dev = "${libdir}/libpytalloc-util.so"
RDEPENDS_pytalloc = "python"

## Original function from waf-samba isn't supporting 
## native build, so let's just override it here
do_configure() {
    export BUILD_ARCH=${BUILD_ARCH}
    export HOST_ARCH=${HOST_ARCH}
    export STAGING_LIBDIR=${STAGING_LIBDIR}
    export STAGING_INCDIR=${STAGING_INCDIR}
    export PYTHONPATH=${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}

    ./configure ${CONFIGUREOPTS} ${EXTRA_OECONF}
}

# The following is a stripped version of waf-samba.bbclass from
# https://raw.githubusercontent.com/openembedded/meta-openembedded/master/meta-networking/classes/waf-samba.bbclass
inherit pythonnative

DEPENDS += "qemu-native libxslt-native docbook-xsl-stylesheets-native python"

CONFIGUREOPTS = " --prefix=${prefix} \
                  --bindir=${bindir} \
                  --sbindir=${sbindir} \
                  --libexecdir=${libexecdir} \
                  --datadir=${datadir} \
                  --sysconfdir=${sysconfdir} \
                  --sharedstatedir=${sharedstatedir} \
                  --localstatedir=${localstatedir} \
                  --libdir=${libdir} \
                  --includedir=${includedir} \
                  --oldincludedir=${oldincludedir} \
                  --infodir=${infodir} \
                  --mandir=${mandir} \
                  ${PACKAGECONFIG_CONFARGS} \
                "

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

def get_waf_parallel_make(d):
    pm = d.getVar('PARALLEL_MAKE')
    if pm:
        # look for '-j' and throw other options (e.g. '-l') away
        # because they might have different meaning in bjam
        pm = pm.split()
        while pm:
            opt = pm.pop(0)
            if opt == '-j':
                v = pm.pop(0)
            elif opt.startswith('-j'):
                v = opt[2:].strip()
            else:
                continue

            v = min(64, int(v))
            return '-j' + str(v)

    return ""

do_compile[progress] = "outof:^\[\s*(\d+)/\s*(\d+)\]\s+"
do_compile () {
    python ./buildtools/bin/waf ${@oe.utils.parallel_make_argument(d, '-j%d', limit=64)}
}

do_install() {
    oe_runmake install DESTDIR=${D}
}
