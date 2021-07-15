SUMMARY = "go.mod: github.com/client9/misspell"
HOMEPAGE = "https://pkg.go.dev/github.com/client9/misspell"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-client9-misspell-sources.inc

EXTRA_DEPENDS += "\
    github.com-gobwas-glob-native \
"

GO_IMPORT = "github.com/client9/misspell"

inherit gosrc
inherit native
