SUMMARY = "go.mod: golang.org/x/xerrors"
HOMEPAGE = "https://pkg.go.dev/golang.org/x/xerrors"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require golang.org-x-xerrors-sources.inc

GO_IMPORT = "golang.org/x/xerrors"

inherit gosrc
inherit native
