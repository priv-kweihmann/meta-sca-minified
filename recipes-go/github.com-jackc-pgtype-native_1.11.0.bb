SUMMARY = "go.mod: github.com/jackc/pgtype"
HOMEPAGE = "https://pkg.go.dev/github.com/jackc/pgtype"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-jackc-pgtype-sources.inc

EXTRA_DEPENDS += "\
    github.com-gofrs-uuid-native \
    github.com-jackc-pgconn-native \
    github.com-jackc-pgio-native \
    github.com-jackc-pgx-v4-native \
    github.com-lib-pq-native \
    github.com-shopspring-decimal-native \
"

GO_IMPORT = "github.com/jackc/pgtype"

inherit gosrc
inherit native
