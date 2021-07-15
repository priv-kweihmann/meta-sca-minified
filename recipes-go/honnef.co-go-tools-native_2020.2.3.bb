SUMMARY = "go.mod: honnef.co/go/tools"
HOMEPAGE = "https://pkg.go.dev/honnef.co/go/tools"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require honnef.co-go-tools-sources.inc

GO_IMPORT = "honnef.co/go/tools"

UPSTREAM_CHECK_URI = "https://github.com/dominikh/go-tools/tags"
UPSTREAM_CHECK_REGEX = "releases/tag/(?P<pver>\d+\.\d+\.\d+)"

inherit gosrc
inherit native
