SUMMARY = "go.mod: github.com/briandowns/spinner"
HOMEPAGE = "https://pkg.go.dev/github.com/briandowns/spinner"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-briandowns-spinner-sources.inc

EXTRA_DEPENDS += "\
    github.com-fatih-color-native \
"

GO_IMPORT = "github.com/briandowns/spinner"

inherit gosrc
inherit native
