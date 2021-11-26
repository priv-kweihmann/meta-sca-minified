SUMMARY = "go.mod: github.com/jcmturner/rpc/v2"
HOMEPAGE = "https://pkg.go.dev/github.com/jcmturner/rpc/v2"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-jcmturner-rpc-v2-sources.inc

EXTRA_DEPENDS += "\
    golang.org-x-net-native \
"

GO_IMPORT = "github.com/jcmturner/rpc/v2"

inherit gosrc
inherit native
