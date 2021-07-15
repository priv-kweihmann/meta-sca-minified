SUMMARY = "go.mod: github.com/gobuffalo/attrs"
HOMEPAGE = "https://pkg.go.dev/github.com/gobuffalo/attrs"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-gobuffalo-attrs-sources.inc

GO_IMPORT = "github.com/gobuffalo/attrs"

inherit gosrc
inherit native
