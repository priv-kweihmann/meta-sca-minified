SUMMARY = "go.mod: github.com/jmoiron/sqlx"
HOMEPAGE = "https://pkg.go.dev/github.com/jmoiron/sqlx"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-jmoiron-sqlx-sources.inc

EXTRA_DEPENDS += "\
    github.com-lib-pq-native \
"

GO_IMPORT = "github.com/jmoiron/sqlx"

inherit gosrc
inherit native
