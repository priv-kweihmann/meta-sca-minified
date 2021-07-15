SUMMARY = "cryptography exposes cryptographic primitives"
DESCRIPTION = "cryptography is a package designed to expose cryptographic primitives and recipes to Python developers"
HOMEPAGE = "https://github.com/pyca/cryptography"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.BSD;md5=5ae30ba4123bc4f2fa49aa0b0dce887b"

DEPENDS += "\
            ${PYTHON_PN}-asn1crypto-native \
            ${PYTHON_PN}-cffi-native \
            ${PYTHON_PN}-six-native \
           "

RDEPENDS_${PN}_class-target += "\
                                ${PYTHON_PN}-asn1crypto \
                                ${PYTHON_PN}-cffi \
                                ${PYTHON_PN}-six \
                               "

PYPI_PACKAGE = "cryptography"

SRC_URI[md5sum] = "aa91e4974981b29e99b863c54034c361"
SRC_URI[sha256sum] = "26409a473cc6278e4c90f782cd5968ebad04d3911ed1c402fc86908c17633e08"

inherit pypi
inherit setuptools3

BBCLASSEXTEND = "native"
