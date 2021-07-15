SUMMARY = "go.mod: gopkg.in/gcfg.v1"
HOMEPAGE = "https://pkg.go.dev/gopkg.in/gcfg.v1"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require gopkg.in-gcfg.v1-sources.inc

EXTRA_DEPENDS += "\
    gopkg.in-warnings.v0-native \
"

GO_IMPORT = "gopkg.in/gcfg.v1"

inherit gosrc
inherit native
