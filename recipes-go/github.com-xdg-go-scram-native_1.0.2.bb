SUMMARY = "go.mod: github.com/xdg-go/scram"
HOMEPAGE = "https://pkg.go.dev/github.com/xdg-go/scram"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-xdg-go-scram-sources.inc

EXTRA_DEPENDS += "\
    github.com-xdg-go-pbkdf2-native \
    github.com-xdg-go-stringprep-native \
"

GO_IMPORT = "github.com/xdg-go/scram"

inherit gosrc
inherit native
