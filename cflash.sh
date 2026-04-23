#!/usr/bin/env bash
# cflash launcher with self-update via git + rebuild (requires sudo for update)

CFLASH_DIR="/usr/local/lib/cflash"
CFLASH_JAR="$CFLASH_DIR/cflash.jar"
GIT_REPO="https://gitea.cametendo.org/cametendo/cflash.git"

update_cflash() {
    # Check for sudo/root
    if [[ $EUID -ne 0 ]]; then
        echo "cflash --update requires root privileges. Please run with sudo."
        exit 1
    fi

    echo "Updating cflash from repository..."

    TMP_DIR=$(mktemp -d)
    echo "Cloning repository into $TMP_DIR..."
    
    if ! git clone --depth 1 "$GIT_REPO" "$TMP_DIR"; then
        echo "Failed to clone repository."
        rm -rf "$TMP_DIR"
        exit 1
    fi

    pushd "$TMP_DIR" >/dev/null

    # Logic to handle if the repo contents are nested inside a 'cflash' folder
    if [[ ! -f "./build.sh" && -d "cflash" ]]; then
        echo "Detected nested directory, moving into cflash/..."
        cd cflash
    fi

    # Ensure build.sh is executable (git usually preserves this, but just in case)
    if [[ -f "./build.sh" ]]; then
        chmod +x ./build.sh
        echo "Building cflash..."
        if ! ./build.sh; then
            echo "Build failed."
            popd >/dev/null
            rm -rf "$TMP_DIR"
            exit 1
        fi
    else
        echo "Error: build.sh not found in the repository root or cflash/ directory."
        popd >/dev/null
        rm -rf "$TMP_DIR"
        exit 1
    fi

    echo "Installing new version..."
    if [[ -f "./install.sh" ]]; then
        chmod +x ./install.sh
        if ! ./install.sh; then
            echo "Install failed."
            popd >/dev/null
            rm -rf "$TMP_DIR"
            exit 1
        fi
    else
        echo "Error: install.sh not found."
        popd >/dev/null
        rm -rf "$TMP_DIR"
        exit 1
    fi

    popd >/dev/null
    rm -rf "$TMP_DIR"
    echo "Update completed successfully!"
    exit 0
}

# Handle --update argument
if [[ "$1" == "--update" ]]; then
    update_cflash
fi

# Run cflash normally
if [[ -f "$CFLASH_JAR" ]]; then
    java --enable-native-access=ALL-UNNAMED -jar "$CFLASH_JAR" "$@"
else
    echo "Error: $CFLASH_JAR not found. Please run 'sudo cflash --update' to install."
    exit 1
fi