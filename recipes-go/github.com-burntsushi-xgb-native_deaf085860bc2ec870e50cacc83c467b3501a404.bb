SUMMARY = "go.mod: github.com/BurntSushi/xgb"
HOMEPAGE = "https://pkg.go.dev/github.com/BurntSushi/xgb"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-burntsushi-xgb-sources.inc

GO_IMPORT = "github.com/BurntSushi/xgb"

inherit gosrc
inherit native
