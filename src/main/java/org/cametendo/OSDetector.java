package org.cametendo;
import java.nio.file.Path;

/**
 * Detects operating system types from image file names and displays completion messages.
 * 
 * <p>This class analyzes the filename of disk images to identify the operating system
 * and displays a personalized completion message with some fun descriptions for various
 * Linux distributions, BSD variants, and other operating systems.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class OSDetector {

    /**
     * Analyzes an image file path and displays a personalized completion message.
     * 
     * <p>Extracts the filename from the provided path and attempts to identify the
     * operating system based on filename patterns. Displays a fun, personalized message
     * wishing the user well with their new OS installation.</p>
     * 
     * @param imagePath Path to the image file that was flashed
     */
    public static void wishWell(String imagePath) {
        String fileName = Path.of(imagePath).getFileName().toString().toLowerCase();
        
        String osName;

        // Specialized & Advanced Distros
        if (fileName.contains("nyarch")) {
            osName = "Nyarch Linux (Nyaa~!)";
        } else if (fileName.contains("artix")) {
            osName = "Artix Linux (Systemd-free Arch!)";
        } else if (fileName.contains("gentoo")) {
            osName = "Gentoo (Enjoy the compiling...)";
        } else if (fileName.contains("nixos")) {
            osName = "NixOS (Immutable & Reproducible!)";
        } else if (fileName.contains("void")) {
            osName = "Void Linux";
        } else if (fileName.contains("arch")) {
            osName = "Arch Linux (btw)";
        } else if (fileName.contains("alpine")) {
            osName = "Alpine Linux (Minimalism at its peak)";
        } else if (fileName.contains("winux")) {
            osName = "Winux (Windows without Windows)";
        }
        // Mainstream Linux
        else if (fileName.contains("fedora")) {
            osName = "Fedora (Freehat Linux)";
        } else if (fileName.contains("debian")) {
            osName = "Debian (Universal OS)";
        } else if (fileName.contains("ubuntu")) {
            osName = "Ubuntu (Debian but fancy)";
        } else if (fileName.contains("mint")) {
            osName = "Linux Mint";
        } else if (fileName.contains("pop-os") || fileName.contains("pop_os")) {
            osName = "Pop!_OS";
        }
        // The BSD Family
        else if (fileName.contains("freebsd")) {
            osName = "FreeBSD";
        } else if (fileName.contains("openbsd")) {
            osName = "OpenBSD (Secure by default)";
        } else if (fileName.contains("netbsd")) {
            osName = "NetBSD (It runs on everything!)";
        }
        // Security & Privacy
        else if (fileName.contains("kali")) {
            osName = "Kali Linux (Happy Hacking)";
        } else if (fileName.contains("tails")) {
            osName = "Tails (Incognito mode: ON)";
        } else if (fileName.contains("qubes")) {
            osName = "Qubes OS (Security by Compartmentalization)";
        }
        // Others & Legacy
        else if (fileName.contains("win") && (fileName.contains("10") || fileName.contains("11"))) {
            osName = "Windows (Spies... Spies everywhere)";
        } else if (fileName.contains("haiku")) {
            osName = "Haiku OS";
        } else if (fileName.contains("reactos")) {
            osName = "ReactOS";
        }
        // Generic Fallbacks
        else if (fileName.contains("linux")) {
            osName = "Linux";
        } else if (fileName.contains("bsd")) {
            osName = "BSD";
        } else {
            osName = "new OS";
        }

        System.out.println("\nFlash complete! Have fun with your " + osName + " installation! 🚀");
    }
}