#!/usr/bin/env bash
# Uninstall script for cflash

if [[ $EUID -ne 0 ]]; then
	echo "Please run as root to uninstall globally."
	exit 1
fi

rm -f /usr/local/bin/cflash
rm -rf /usr/local/lib/cflash

echo "cflash uninstalled successfully."
