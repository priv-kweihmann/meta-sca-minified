SUMMARY = "go.mod: github.com/go-git/go-git/v5"
HOMEPAGE = "https://pkg.go.dev/github.com/go-git/go-git/v5"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-go-git-go-git-v5-sources.inc

GO_IMPORT = "github.com/go-git/go-git/v5"

inherit gosrc
inherit native
