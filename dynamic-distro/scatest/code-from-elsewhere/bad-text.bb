SUMMARY = "Bad text testcode"
DEFAULT_PREFERENCE = "-1"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${SCA_LAYERDIR}/LICENSE;md5=a4a2bbea1db029f21b3a328c7a059172"

SRC_URI = "file://sample.txt;subdir=source"

S = "${WORKDIR}/source"
B = "${WORKDIR}/source"

inherit sca
