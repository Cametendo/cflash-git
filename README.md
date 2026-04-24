# cflash
A small and lightweight wrapper for `dd` that strips away the complexity of CLI flashing.

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-21%2B-orange.svg)](https://www.java.com/en/)
[![Platform](https://img.shields.io/badge/Platform-Linux-brightgreen.svg)](https://www.linux.org/)
[![Version](https://img.shields.io/badge/version-1.0.0-blue)](https://github.com/Cametendo/cflash-git/releases/tag/cflash-1.0.0)

# About
Small and lightweight wrapper written in Java for `dd` designed to simplify CLI flashing while protecting your hardware from accidental command-line errors. cflash replaces the syntax with a clear, safe interface, providing a reliable workflow for both newcomers and power users.

# Requirements
- `Java`: 21 (Download [here](https://www.oracle.com/java/technologies/downloads/#java21))
- `util-linux`: 2.41
- `coreutils`: 9.10
- `maven`: 3.9.15 
- Operating System: Linux
### Building and Running Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/cametendo/cflash-git.git
   cd cflash-git
   ```

2. Compile the code:

   ```bash
   mvn clean package
   ```

3. Run cflash:

   ```bash
   java -jar target/cflash-<version>.jar (optionally add arguments here, like with dd)
   ```

4. Update cflash:

   ```bash
   git pull
   mvn clean package
   ```

### System-wide Installation

To install cflash globally so that it can be run from any terminal:

1. Clone the repository (if not done already):

   ```bash
   git clone https://github.com/cametendo/cflash-git.git
   cd cflash-git
   ```

2. Make the build and install scripts executable:

   ```bash
   chmod +x build.sh install.sh
   ```

3. Build the project using the provided build script (requires root privileges):

   ```bash
   sudo ./build.sh
   ```

4. Install globally (requires root privileges):

   ```bash
   sudo ./install.sh
   ```

5. Run cflash from anywhere:

   ```bash
   cflash
   ```

6. Update cflash (reguires root privileges):

   ```bash
   sudo cflash --update
   ```

**Notes:**

* The `build.sh` script compiles all Java source files and creates an executable `cflash.jar`.
* The `install.sh` script copies `cflash.jar` to `/usr/local/lib/cflash` and installs a wrapper script in `/usr/local/bin` for easy execution.
* If you use Arch Linux:
   * You can directly install it with `yay` or `paru` via `yay -S cflash` / `paru -S cflash` or
   * You can clone the repository from the [AUR](https://aur.archlinux.org/packages/cflash) and manually build it:
      ```bash
      git clone https://aur.archlinux.org/cflash.git
      cd cflash
      makepkg -si
      ```
   * You can update cflash using `yay` / `paru` via `yay -S cflash` / `paru -S cflash` or by rebuilding the package:
      ```bash
      git clone https://aur.archlinux.org/cflash.git
      cd cflash
      makepkg -si
      ```

# Usage
- Using the command `cflash` in the terminal, will start the flashing process. You will be asked several question before the flashing begins:
    1. You will see a list of every drive your system see's (excluding system drives) and the be asked to enter the device you want to flash the image onto. (F.e. **/dev/sda**)
    2. You will be prompted to enter the path of the iso / image you want to flash
    3. You will be prompted to choose a byte size (default: 4M)
    4. You will be prompted to enter your oflag (default: direct)
    5. You will be asked if you are absolutely sure that you want to continue (flashing will wipe all data)
- Alternative: using `cflash [device] [iso-path] [block-size] [oflag]` will skip the questions and instantly ask you, if you're absolutely sure you want to continue.
- Once confirmed, the flash will start and a small progress bar will appear showing the flashing progress.
- After completion, the program will detect the OS from the iso and wish you a great time with your new OS. (Example: "Done! Have fun with your new Linux installation!)
- **IMPORTANT**: Since dd needs sudo rights, ensure you have root priviliges.

# Supported OS
- Linux

# Installation
1. Clone the repository onto your device and cd into it.
2. Run the `build.sh` file to build the program.
3. Run the `ìnstall.sh`to install the program.
4. Open a terminal and use the program with `cflash`, optionally add all the arguments you need.

# License and Credits
**Author**: [Cametendo](https://www.github.com/Cametendo)
**License**: MIT
