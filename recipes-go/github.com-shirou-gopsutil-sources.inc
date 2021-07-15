SRC_URI += "https://proxy.golang.org/github.com/shirou/gopsutil/@v/v3.21.4+incompatible.zip;srcoutput=github.com/shirou/gopsutil;srcinput=github.com/shirou/gopsutil@v3.21.4+incompatible;downloadfilename=github-com-shirou-gopsutil-3.21.4+incompatible.zip;name=github-com-shirou-gopsutil"
SRC_URI[github-com-shirou-gopsutil.sha256sum] = "8d7437ac8806601bb09ef128a941cc6437c595bdd2c14a3edaf3373a198f51e5"

GOSRC_LICENSE += "\
    BSD-3-Clause \
"
LIC_FILES_CHKSUM += "\
    file://src/github.com/shirou/gopsutil/LICENSE;md5=ed7522382cec5b7a6d6ebb8e30eed40e \
"

GOSRC_INCLUDEGUARD += "github.com-shirou-gopsutil-sources.inc"

require ${@bb.utils.contains('GOSRC_INCLUDEGUARD', 'github.com-tklauser-go-sysconf-sources.inc', '', 'github.com-tklauser-go-sysconf-sources.inc', d)}
require ${@bb.utils.contains('GOSRC_INCLUDEGUARD', 'golang.org-x-sys-sources.inc', '', 'golang.org-x-sys-sources.inc', d)}