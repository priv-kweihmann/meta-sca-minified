SUMMARY = "go.mod: github.com/creack/pty"
HOMEPAGE = "https://pkg.go.dev/github.com/creack/pty"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-creack-pty-sources.inc

GO_IMPORT = "github.com/creack/pty"

inherit gosrc
inherit native
