SUMMARY = "go.mod: github.com/fyne-io/gl-js"
HOMEPAGE = "https://pkg.go.dev/github.com/fyne-io/gl-js"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-fyne-io-gl-js-sources.inc

EXTRA_DEPENDS += "\
    nativesdk-github.com-go-gl-gl \
    nativesdk-github.com-gopherjs-gopherjs \
"
GO_IMPORT = "github.com/fyne-io/gl-js"
# needs X11 EGL3 and more
do_compile[noexec] = "1"
inherit gosrc
inherit nativesdk
