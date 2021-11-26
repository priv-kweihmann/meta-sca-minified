SUMMARY = "go.mod: github.com/mozilla/scribe"
HOMEPAGE = "https://pkg.go.dev/github.com/mozilla/scribe"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-mozilla-scribe-sources.inc

EXTRA_DEPENDS += "\
    github.com-lib-pq-native \
    gopkg.in-yaml.v2-native \
"

GO_IMPORT = "github.com/mozilla/scribe"

inherit gosrc
inherit native
