SUMMARY = "go.mod: github.com/jackc/pgx/v4"
HOMEPAGE = "https://pkg.go.dev/github.com/jackc/pgx/v4"
DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-jackc-pgx-v4-sources.inc

EXTRA_DEPENDS += "\
    github.com-go-kit-log-native \
    github.com-jackc-pgconn-native \
    github.com-jackc-pgio-native \
    github.com-jackc-pgproto3-v2-native \
    github.com-jackc-pgtype-native \
    github.com-jackc-puddle-native \
    github.com-rs-zerolog-native \
    github.com-sirupsen-logrus-native \
    go.uber.org-zap-native \
    gopkg.in-inconshreveable-log15.v2-native \
"

GO_IMPORT = "github.com/jackc/pgx/v4"

inherit gosrc
inherit native
