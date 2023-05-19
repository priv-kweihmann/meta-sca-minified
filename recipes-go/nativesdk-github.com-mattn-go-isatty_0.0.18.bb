SUMMARY = "go.mod: github.com/mattn/go-isatty"
HOMEPAGE = "https://pkg.go.dev/github.com/mattn/go-isatty"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-mattn-go-isatty-sources.inc

EXTRA_DEPENDS += "nativesdk-golang.org-x-sys"
GO_IMPORT = "github.com/mattn/go-isatty"
inherit gosrc
inherit nativesdk
