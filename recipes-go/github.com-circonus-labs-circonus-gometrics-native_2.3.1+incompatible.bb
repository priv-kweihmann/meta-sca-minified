SUMMARY = "go.mod: github.com/circonus-labs/circonus-gometrics"
HOMEPAGE = "https://pkg.go.dev/github.com/circonus-labs/circonus-gometrics"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-circonus-labs-circonus-gometrics-sources.inc

EXTRA_DEPENDS += "\
    github.com-circonus-labs-circonusllhist-native \
    github.com-hashicorp-go-retryablehttp-native \
    github.com-pkg-errors-native \
    github.com-tv42-httpunix-native \
"

GO_IMPORT = "github.com/circonus-labs/circonus-gometrics"

inherit gosrc
inherit native
