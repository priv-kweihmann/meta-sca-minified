SUMMARY = "go.mod: github.com/miekg/dns"
HOMEPAGE = "https://pkg.go.dev/github.com/miekg/dns"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-miekg-dns-sources.inc

GO_IMPORT = "github.com/miekg/dns"

inherit gosrc
inherit native
