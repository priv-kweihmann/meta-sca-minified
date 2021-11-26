SUMMARY = "go.mod: github.com/inconshreveable/log15"
HOMEPAGE = "https://pkg.go.dev/github.com/inconshreveable/log15"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-inconshreveable-log15-sources.inc

EXTRA_DEPENDS += "\
    github.com-go-stack-stack-native \
    github.com-mattn-go-colorable-native \
    github.com-mattn-go-isatty-native \
"

GO_IMPORT = "github.com/inconshreveable/log15"

inherit gosrc
inherit native
