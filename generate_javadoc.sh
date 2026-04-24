#!/usr/bin/env bash
# --- Generate JavaDoc for cflash ---
# This script generates developer documentation using javadoc.
# If JDK is missing, it prompts the user to install it using the detected package manager.

set -e

SRC_DIR="src/main/java"
DOC_DIR="docs"

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

# --- Generate JDK install command ---
jdk_install_command() {
	local pm="$1"
	case "$pm" in
		pacman) echo "pacman -Sy --noconfirm jdk-openjdk" ;;
		apt) echo "apt update && apt install -y openjdk-21-jdk || apt install -y default-jdk" ;;
		dnf) echo "dnf install -y java-21-openjdk-devel || dnf install -y java-latest-openjdk-devel" ;;
		yum) echo "yum install -y java-21-openjdk-devel || yum install -y java-latest-openjdk-devel" ;;
		zypper) echo "zypper install -y java-21-openjdk-devel || zypper install -y java-latest-openjdk-devel" ;;
		brew) echo "brew install openjdk" ;;
		apk) echo "apk add openjdk21" ;;
		emerge) echo "emerge dev-java/openjdk-bin" ;;
		*) echo "" ;;
	esac
}

# --- Check for javadoc ---
if ! command -v javadoc >/dev/null 2>&1; then
	echo "Java Development Kit (JDK) with javadoc not found."
	PM=$(detect_package_manager)

	if [[ -z "$PM" ]]; then
		echo "Please install the latest JDK manually and rerun this script."
		exit 1
	fi

	CMD=$(jdk_install_command "$PM")
	if [[ $EUID -ne 0 && "$PM" != "brew" ]]; then
		echo "Please rerun this script with sudo if you want automatic JDK installation."
		exit 1
	fi

	# Prompt user
	read -rp "Do you want to run the following command to install the JDK? [$CMD] (y/n): " ANSWER
	case "$ANSWER" in
		y|Y)
			echo "Installing JDK..."
			eval "$CMD"
			echo "JDK installed successfully."
			;;
		*)
			echo "JDK installation cancelled. Please install manually and rerun."
			exit 1
			;;
	esac
fi

# --- Check if source directory exists ---
if [[ ! -d "$SRC_DIR" ]]; then
	echo -e "\033[31mSource directory '$SRC_DIR' not found!"
	echo "Please ensure you're running this script from the project root directory."
	exit 1
fi

# --- Generate JavaDoc ---
echo "Generating JavaDoc for cflash..."
rm -rf "$DOC_DIR"
mkdir -p "$DOC_DIR"

# Check if Maven is available
if command -v mvn >/dev/null 2>&1; then
	echo "Using Maven to generate JavaDoc with dependencies..."
	if mvn javadoc:javadoc -Dquiet=true > /dev/null 2>&1; then
		# Copy generated docs from Maven location to our docs directory
		if [[ -d "target/reports/apidocs" ]]; then
			cp -r target/reports/apidocs/* "$DOC_DIR/"
			echo -e "\033[32mJavaDoc generated successfully using Maven!"
			echo -e "\033[0m→ Open file://$PWD/$DOC_DIR/index.html to view it."
		else
			echo -e "\033[31mMaven generated docs but couldn't find them in target/reports/apidocs"
			exit 1
		fi
	else
		echo -e "\033[31mMaven JavaDoc generation failed."
		exit 1
	fi
else
	echo "Maven not found. Attempting to generate JavaDoc without dependencies..."
	# Try to download JLine dependency manually
	JLINE_JAR="$HOME/.m2/repository/org/jline/jline/3.25.1/jline-3.25.1.jar"
	if [[ -f "$JLINE_JAR" ]]; then
		echo "Found JLine dependency in Maven local repository..."
		CLASSPATH="$SRC_DIR:$JLINE_JAR"
	else
		echo "JLine dependency not found. JavaDoc may have missing links."
		CLASSPATH="$SRC_DIR"
	fi
	
	if javadoc -quiet -d "$DOC_DIR" \
		-sourcepath "$SRC_DIR" \
		-classpath "$CLASSPATH" \
		-subpackages org.cametendo \
		-private \
		-author \
		-version \
		-doctitle "cflash - Disk Image Flashing Utility" \
		-windowtitle "cflash Documentation" \
		-bottom "Copyright &#169; 2026 Cametendo. All rights reserved." > /dev/null 2>&1; then
		echo -e "\033[32mJavaDoc generated successfully!"
		echo -e "\033[0m→ Open file://$PWD/$DOC_DIR/index.html to view it."
	else
		echo -e "\033[31mJavaDoc generation failed. Install Maven for better dependency handling."
		echo "Run: apt install maven (Ubuntu/Debian) or pacman -S maven (Arch)"
		exit 1
	fi
fi
