SUMMARY = "go.mod: github.com/monoculum/formam"
HOMEPAGE = "https://pkg.go.dev/github.com/monoculum/formam"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-monoculum-formam-sources.inc

GO_IMPORT = "github.com/monoculum/formam"

inherit gosrc
inherit native
