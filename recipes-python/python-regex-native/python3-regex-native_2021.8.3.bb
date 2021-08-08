SUMMARY = "Alternative regular expression module, to replace re"
HOMEPAGE = "https://bitbucket.org/mrabarnett/mrab-regex"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=7b5751ddd6b643203c31ff873051d069"

PYPI_PACKAGE = "regex"

SRC_URI[md5sum] = "1a69958cf7f0a3bcba24482116af2ef9"
SRC_URI[sha256sum] = "8935937dad2c9b369c3d932b0edbc52a62647c2afb2fafc0c280f14a8bf56a6a"

inherit pypi
inherit setuptools3
inherit native
