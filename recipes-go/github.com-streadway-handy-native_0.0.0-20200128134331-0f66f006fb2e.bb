SUMMARY = "go.mod: github.com/streadway/handy"
HOMEPAGE = "https://pkg.go.dev/github.com/streadway/handy"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-streadway-handy-sources.inc

GO_IMPORT = "github.com/streadway/handy"

inherit gosrc
inherit native
