SUMMARY = "go.mod: github.com/cactus/go-statsd-client"
HOMEPAGE = "https://pkg.go.dev/github.com/cactus/go-statsd-client"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-cactus-go-statsd-client-sources.inc

EXTRA_DEPENDS += "\
    github.com-jessevdk-go-flags-native \
"

GO_IMPORT = "github.com/cactus/go-statsd-client"

UPSTREAM_CHECK_IMPORT_SUFFIX = "/v5"

inherit gosrc
inherit native
