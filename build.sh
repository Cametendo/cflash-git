#!/usr/bin/env bash
# --- Build script for cflash ---
# Compiles sources and bundles jline via Maven into a single fat JAR.
# If Maven is missing, prompts the user to install it using the detected package manager.

set -e

JAR_FILE="cflash.jar"

# --- Detect package manager ---
detect_package_manager() {
	if command -v pacman >/dev/null 2>&1; then
		echo "pacman"
	elif command -v apt >/dev/null 2>&1; then
		echo "apt"
	elif command -v dnf >/dev/null 2>&1; then
		echo "dnf"
	elif command -v yum >/dev/null 2>&1; then
		echo "yum"
	elif command -v zypper >/dev/null 2>&1; then
		echo "zypper"
	elif command -v brew >/dev/null 2>&1; then
		echo "brew"
	elif command -v apk >/dev/null 2>&1; then
		echo "apk"
	elif command -v emerge >/dev/null 2>&1; then
		echo "emerge"
	else
		echo ""
	fi
}

# --- Generate Maven install command ---
maven_install_command() {
	local pm="$1"
	case "$pm" in
		pacman) echo "pacman -Sy --noconfirm maven" ;;
		apt) echo "apt update && apt install -y maven" ;;
		dnf) echo "dnf install -y maven" ;;
		yum) echo "yum install -y maven" ;;
		zypper) echo "zypper install -y maven" ;;
		brew) echo "brew install maven" ;;
		apk) echo "apk add maven" ;;
		emerge) echo "emerge dev-java/maven-bin" ;;
		*) echo "" ;;
	esac
}

# --- Check for mvn ---
if ! command -v mvn >/dev/null 2>&1; then
	echo "Maven (mvn) not found."
	PM=$(detect_package_manager)

	if [[ -z "$PM" ]]; then
		echo "Please install Maven manually and rerun this script."
		exit 1
	fi

	CMD=$(maven_install_command "$PM")
	if [[ $EUID -ne 0 && "$PM" != "brew" ]]; then
		echo "Please run this script as root to install Maven, or install it manually."
		exit 1
	fi

	read -rp "Do you want to run the following command to install Maven? [$CMD] (y/n): " ANSWER
	case "$ANSWER" in
		y|Y)
			echo "Installing Maven..."
			eval "$CMD"
			echo "Maven installed successfully."
			;;
		*)
			echo "Maven installation cancelled. Please install manually and rerun."
			exit 1
			;;
	esac
fi

# --- Build process ---
echo "Building cflash with Maven..."
mvn package -q

echo "Copying fat JAR to ${JAR_FILE}..."
cp target/cflash-*-jar-with-dependencies.jar "$JAR_FILE"

echo "Build completed successfully: ${JAR_FILE}"
