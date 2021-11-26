SUMMARY = "go.mod: github.com/hashicorp/mdns"
HOMEPAGE = "https://pkg.go.dev/github.com/hashicorp/mdns"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-hashicorp-mdns-sources.inc

EXTRA_DEPENDS += "\
    github.com-miekg-dns-native \
    golang.org-x-net-native \
"

GO_IMPORT = "github.com/hashicorp/mdns"

inherit gosrc
inherit native
