SUMMARY = "go.mod: github.com/onsi/ginkgo"
HOMEPAGE = "https://pkg.go.dev/github.com/onsi/ginkgo"

# License is determined by the modules included and will be therefore computed
LICENSE = "${@' & '.join(sorted(set(x for x in (d.getVar('GOSRC_LICENSE') or '').split(' ') if x)))}"

# inject the needed sources
require github.com-onsi-ginkgo-sources.inc

EXTRA_DEPENDS += "\
    github.com-go-task-slim-sprig-native \
    github.com-nxadm-tail-native \
    github.com-onsi-gomega-native \
    golang.org-x-sys-native \
    golang.org-x-tools-native \
"

GO_IMPORT = "github.com/onsi/ginkgo"

inherit gosrc
inherit native
