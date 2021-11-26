SUMMARY = "go.mod: github.com/modern-go/concurrent"
HOMEPAGE = "https://pkg.go.dev/github.com/modern-go/concurrent"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-modern-go-concurrent-sources.inc

GO_IMPORT = "github.com/modern-go/concurrent"

inherit gosrc
inherit native
