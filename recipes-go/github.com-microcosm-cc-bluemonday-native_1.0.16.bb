SUMMARY = "go.mod: github.com/microcosm-cc/bluemonday"
HOMEPAGE = "https://pkg.go.dev/github.com/microcosm-cc/bluemonday"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-microcosm-cc-bluemonday-sources.inc

EXTRA_DEPENDS += "\
    github.com-aymerick-douceur-native \
    golang.org-x-net-native \
"

GO_IMPORT = "github.com/microcosm-cc/bluemonday"

inherit gosrc
inherit native
