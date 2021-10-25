SUMMARY = "go.mod: github.com/coreos/go-semver"
HOMEPAGE = "https://pkg.go.dev/github.com/coreos/go-semver"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-coreos-go-semver-sources.inc

GO_IMPORT = "github.com/coreos/go-semver"

inherit gosrc
inherit native
