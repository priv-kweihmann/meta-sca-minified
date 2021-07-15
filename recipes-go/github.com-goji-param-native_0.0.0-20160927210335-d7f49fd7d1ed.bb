SUMMARY = "go.mod: github.com/goji/param"
HOMEPAGE = "https://pkg.go.dev/github.com/goji/param"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-goji-param-sources.inc

GO_IMPORT = "github.com/goji/param"

inherit gosrc
inherit native
