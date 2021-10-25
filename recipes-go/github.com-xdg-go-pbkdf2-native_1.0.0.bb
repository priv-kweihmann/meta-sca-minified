SUMMARY = "go.mod: github.com/xdg-go/pbkdf2"
HOMEPAGE = "https://pkg.go.dev/github.com/xdg-go/pbkdf2"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-xdg-go-pbkdf2-sources.inc

GO_IMPORT = "github.com/xdg-go/pbkdf2"

inherit gosrc
inherit native