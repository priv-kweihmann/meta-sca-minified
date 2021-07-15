SUMMARY = "go.mod: github.com/alexkohler/prealloc"
HOMEPAGE = "https://pkg.go.dev/github.com/alexkohler/prealloc"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-alexkohler-prealloc-sources.inc

GO_IMPORT = "github.com/alexkohler/prealloc"

inherit gosrc
inherit native
