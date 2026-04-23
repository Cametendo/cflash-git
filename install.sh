#!/usr/bin/env bash
# Install script for system-wide usage

# Ensure running as root
if [[ $EUID -ne 0 ]]; then
	echo "Please run as root to install globally."
	exit 1
fi

# Create installation directories
mkdir -p /usr/local/lib/cflash
cp cflash.jar /usr/local/lib/cflash/
cp cflash.sh /usr/local/bin/cflash
chmod +x /usr/local/bin/cflash

echo "cflash installed successfully! You can now run 'cflash' from anywhere."
