require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(beaglebone)"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "a"

FILESPATH =. "${FILE_DIRNAME}/linux-mainline-3.14:${FILE_DIRNAME}/linux-mainline-3.14/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.14"

SRC_URI = "https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-3.14.y"
SRCREV_pn-${PN} = "387df1bd3fc46bc695b317dda38b3254f4409036"

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.bin ] ; then
		cp ${WORKDIR}/am335x-pm-firmware.bin ${S}/firmware
	fi
}

SRC_URI += " \
	file://dts/0001-arm-dts-am335x-boneblack-lcdc-add-panel-info.patch \
	file://dts/0002-arm-dts-am335x-boneblack-add-cpu0-opp-points.patch \
	file://dts/0003-arm-dts-am335x-bone-common-enable-and-use-i2c2.patch \
	file://dts/0004-arm-dts-am335x-bone-common-setup-default-pinmux-http.patch \
	file://fixes/0001-pinctrl-pinctrl-single-must-be-initialized-early.patch \
	file://usb/0001-usb-musb-musb_host-Enable-ISOCH-IN-handling-for-AM33.patch \
	file://usb/0002-usb-musb-musb_cppi41-Make-CPPI-aware-of-high-bandwid.patch \
	file://usb/0003-usb-musb-musb_cppi41-Handle-ISOCH-differently-and-no.patch \
	file://dts-bone/0001-arm-dts-am335x-bone-common-add-uart2_pins-uart4_pins.patch \
	file://dts-bone-capes/0001-capes-ttyO1-ttyO2-ttyO4.patch \
	file://dts-bone-capes/0002-capes-Makefile.patch \
	file://static-capes/0001-Added-Argus-UPS-cape-support.patch \
	file://static-capes/0002-Added-Argus-UPS-cape-support-BBW.patch \
	file://defconfig \
  file://am335x-pm-firmware.bin \
"
