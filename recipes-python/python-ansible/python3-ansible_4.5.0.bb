SUMMARY = "Ansible is the simplest way to automate apps and IT infrastructure"
DESCRIPTION = "Ansible is a simple IT automation platform that makes your applications and systems easier to deploy"
HOMEPAGE = "https://github.com/ansible/ansible/"
SECTION = "devel/python"

DEPENDS:class-native += "\
    python3-ansible-core-native \
    python3-cryptography-native \
    python3-jinja2-native \
    python3-packaging-native \
    python3-pyyaml-native \
    python3-resolvelib-native \
"

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

PYPI_PACKAGE = "ansible"

SRC_URI[md5sum] = "1111e4a7d0e0b7ec5ce2c02fa12d1383"
SRC_URI[sha256sum] = "cc5352b2351a381015ece79eab783a1b0668f97b377810fed3c746e2f1d50db1"

inherit pypi
inherit setuptools3

do_install:append() {
    # replace hardcoded python shebang
    find ${D} -type f -exec sed -i -e '1s,#\!.*python3,#! /usr/bin/env python3,' {} \;
    # remove blanks from filename
    mv "${D}${PYTHON_SITEPACKAGES_DIR}/ansible_collections/community/network/tests/integration/targets/ce_is_is_view/tests/netconf/test_ce_is_is_view_entity .yaml" \
       "${D}${PYTHON_SITEPACKAGES_DIR}/ansible_collections/community/network/tests/integration/targets/ce_is_is_view/tests/netconf/test_ce_is_is_view_entity.yaml"
}

RDEPENDS:${PN}:class-target += "\
    bash \
    python3-ansible-core \
    python3-cryptography \
    python3-jinja2 \
    python3-modules \
    python3-packaging \
    python3-pyyaml \
    python3-resolvelib \
"

BBCLASSEXTEND = "native"
