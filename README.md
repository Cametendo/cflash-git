# cflash
Small and lightweight image and iso flasher build on `dd`. 

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-21%2B-orange.svg)](https://www.java.com/en/)
[![Platform](https://img.shields.io/badge/Platform-Linux-brightgreen.svg)](https://www.linux.org/)
[![Status](https://img.shields.io/badge/Status-Beta-red.svg)](https://en.wikipedia.org/wiki/Software_release_life_cycle#Beta)

# About
Java program using `dd` to make flashing iso and image files easier on the terminal. This program allows anyone to flash iso and image files without having to search for extra GUI tools by keeping it simple and resource-friendly.

# Requirements
- `Java`: 21 (Download [here](https://www.oracle.com/java/technologies/downloads/#java21)
- `util-linux`: 2.41
- `coreutils`: 9.10

# Usage
- Using the command `cflash` in the terminal, will start the flashing process. You will be asked several question before the flashing begins:
    1. You will see a list of every drive your system see's (excluding system drives) and the be asked to enter the device you want to flash the image onto. (F.e. **/dev/sda**)
    2. You will be prompted to enter the path of the iso / image you want to flash
    3. You will be prompted to choose a byte size (default: 4M)
    4. You will be prompted to enter your oflag (default: direct)
    5. You will be asked if you are absolutely sure that you want to continue (flashing will wipe all data)
- Alternative: using `cflash [device] [iso-path] [block-size] [oflag]` will skip the first question and instantly ask you, if you're absolutely sure you want to continue.
- Once confirmed, the flash will start and a small progress bar will appear showing the flashing progress.
- After completion, the program will detect the OS from the iso and wish you a great time with your new OS. (Example: "Done! Have fun with your new Linux installation!)
- **IMPORTANT**: Since dd needs sudo rights, ensure you have root priviliges.

# Supported OS
- Linux, MacOS, FreeBSD

# Installation
1. Clone the repository onto your local device.
2. Run the `build.sh` file to build the program.
3. Run the `ìnstall.sh`to install the program.
4. Open a terminal and use the program with `cflash`.

# License and Credits
**Author**: [Cametendo](https://www.github.com/Cametendo)
**License**: MIT
