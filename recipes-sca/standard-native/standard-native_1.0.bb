SUMMARY = "sca definition for standard"

DEFAULT_PREFERENCE = "${SCA_DEFAULT_PREFERENCE}"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${SCA_LAYERDIR}/LICENSE;md5=a4a2bbea1db029f21b3a328c7a059172"

DEPENDS += "npm-standard-native"

inherit sca-description
inherit native

SCA_TOOL_DESCRIPTION = "standard"
