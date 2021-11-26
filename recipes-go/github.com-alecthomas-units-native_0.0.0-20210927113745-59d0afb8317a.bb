SUMMARY = "go.mod: github.com/alecthomas/units"
HOMEPAGE = "https://pkg.go.dev/github.com/alecthomas/units"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-alecthomas-units-sources.inc

GO_IMPORT = "github.com/alecthomas/units"

inherit gosrc
inherit native
