SUMMARY = "go.mod: github.com/dgrijalva/jwt-go"
HOMEPAGE = "https://pkg.go.dev/github.com/dgrijalva/jwt-go"

# License is determined by the modules included and will be therefore computed
DEFAULT_PREFERENCE = "-1"
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-dgrijalva-jwt-go-sources.inc

GO_IMPORT = "github.com/dgrijalva/jwt-go"

inherit gosrc
inherit native
