SUMMARY = "go.mod: github.com/mitchellh/mapstructure"
HOMEPAGE = "https://pkg.go.dev/github.com/mitchellh/mapstructure"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-mitchellh-mapstructure-sources.inc

GO_IMPORT = "github.com/mitchellh/mapstructure"

inherit gosrc
inherit native
