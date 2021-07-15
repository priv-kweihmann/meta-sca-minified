SUMMARY = "Checks playbooks for practices and behaviour that could potentially be improved"
HOMEPAGE = "https://github.com/ansible/ansible-lint"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://ansible_lint-${PV}.dist-info/LICENSE;md5=27006a41205763794866943ed14d2493"

DEPENDS += "\
            ${PYTHON_PN}-ansible-native \
            ${PYTHON_PN}-bcrypt-native \
            ${PYTHON_PN}-cryptography-native \
            ${PYTHON_PN}-paramiko-native \
            ${PYTHON_PN}-pathlib2-native \
            ${PYTHON_PN}-pyasn1-native \
            ${PYTHON_PN}-pynacl-native \
            ${PYTHON_PN}-pyyaml-native \
            ${PYTHON_PN}-ruamelyaml-native \
            ${PYTHON_PN}-scandir-native \
            ${PYTHON_PN}-setuptools-scm-git-archive-native \
            ${PYTHON_PN}-setuptools-scm-native \
            ${PYTHON_PN}-six-native \
            ${PYTHON_PN}-rich-native \
            "

# wheel-files are just zip-files, so we can threat them like that
SRC_URI = "https://files.pythonhosted.org/packages/88/bf/12e507b1d5b3a6317c515c27cfef869168dbe7dd879f9e272f683fb460b1/ansible_lint-4.3.5-py2.py3-none-any.whl;downloadfilename=ansible_lint-${PV}-py2.py3-none-any.zip;subdir=${BP} \
           file://ansiblelint.sca.description"

SRC_URI[md5sum] = "f8569cc488bdc63cdaf3fafc7d696924"
SRC_URI[sha256sum] = "4eabcea3572a9a3d85c6abefe04ca80ec8b306f50116ee6a9d118b4f2fd5e192"

UPSTREAM_CHECK_URI ?= "https://pypi.python.org/pypi/ansible_lint/"
UPSTREAM_CHECK_REGEX = "ansible_lint-(?P<pver>\d+\.\d+\.\d+)-py2\.py3-none-any\.whl"

do_unpack[depends] += "unzip-native:do_populate_sysroot"

inherit python3native
inherit sca-description
inherit native

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/ansiblelint
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/ansible_lint-${PV}.dist-info

    cp -r ${S}/ansiblelint/* ${D}${libdir}/${PYTHON_DIR}/site-packages/ansiblelint/
    install -m 644 ${S}/ansible_lint-${PV}.dist-info/* ${D}${libdir}/${PYTHON_DIR}/site-packages/ansible_lint-${PV}.dist-info/

    install -d ${D}${datadir}
    install ${WORKDIR}/ansiblelint.sca.description ${D}${datadir}
}

FILES_${PN} += "${datadir} ${PYTHON_SITEPACKAGES_DIR}"
