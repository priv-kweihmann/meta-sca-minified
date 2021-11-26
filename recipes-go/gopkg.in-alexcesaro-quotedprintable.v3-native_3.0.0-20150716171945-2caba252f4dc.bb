SUMMARY = "go.mod: gopkg.in/alexcesaro/quotedprintable.v3"
HOMEPAGE = "https://pkg.go.dev/gopkg.in/alexcesaro/quotedprintable.v3"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require gopkg.in-alexcesaro-quotedprintable.v3-sources.inc

GO_IMPORT = "gopkg.in/alexcesaro/quotedprintable.v3"

inherit gosrc
inherit native
