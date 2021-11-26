SUMMARY = "go.mod: github.com/juju/ratelimit"
HOMEPAGE = "https://pkg.go.dev/github.com/juju/ratelimit"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-juju-ratelimit-sources.inc

GO_IMPORT = "github.com/juju/ratelimit"

inherit gosrc
inherit native
