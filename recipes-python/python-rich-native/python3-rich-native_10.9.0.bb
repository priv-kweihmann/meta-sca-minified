SUMMARY = "Render rich text, tables, progress bars, syntax highlighting"
HOMEPAGE = "https://github.com/willmcgugan/rich"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d0d35d5357392e5bfeb0d0a7e6ba4d83"

DEPENDS += "\
            python3-colorama-native \
            python3-commonmark-native \
            python3-pygments-native \
            python3-typing-extensions-native \
           "

PYPI_PACKAGE = "rich"

SRC_URI[md5sum] = "f09a5c06538d45bddcd298669bff2369"
SRC_URI[sha256sum] = "ba285f1c519519490034284e6a9d2e6e3f16dc7690f2de3d9140737d81304d22"

inherit pypi
inherit setuptools3
inherit native
