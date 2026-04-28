package org.cametendo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Handles storage device detection, listing, and validation for the flashing process.
 * * <p>Includes safety checks to ensure we don't nuke a partition that's currently in use.
 * Keeps the user in the loop with a more personal touch.</p>
 * * @author Cametendo
 * @version 1.1
 */
public class StorageDeviceLister {

    public static String device = "";
    public static String fullPath = "";

    /**
     * Guides the user through selecting a safe, unmounted storage device.
     */
    protected static String deviceCheck(Scanner UserInput) {
        deviceList();

        while (true) {
            System.out.print("Target device: ");
            device = UserInput.nextLine().trim();
            
            if (device.isBlank()) {
                System.out.println("Oops... Device name is empty. Did you missclick?");
                continue;
            }

            // Standardize path - allows entering 'sda' or '/dev/sda'
            String checkPath = device.startsWith("/dev/") ? device : "/dev/" + device;
            Path path = Path.of(checkPath);

            try {
                fullPath = path.toRealPath().toString();

                // Check if the user is about to break their system
                if (isMounted(fullPath)) {
                    System.out.println("Wait a second! " + fullPath + " is currently mounted.");
                    System.out.println("I can't flash to a device that's in use. Unmount it and try again!");
                    continue;
                }
                
                System.out.println("Solid choice. Using device: " + fullPath);
                return fullPath; 

            } catch (IOException e) {
                System.out.println("Hmm... I can't seem to find or access that device. Are you sure you have access to it?");
            }
        }
    }

    /**
     * Asks lsblk if the device or its children have an active mount point.
     */
    private static boolean isMounted(String devicePath) {
        try {
            ProcessBuilder pb = new ProcessBuilder("lsblk", "-no", "MOUNTPOINT", devicePath);
            Process process = pb.start();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        return true;
                    }
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Warning: Mountstatus couldn't be verified");
            return true; 
        }
        return false;
    }

    /**
     * Lists devices with enough info for the user to make a smart decision.
     */
    private static void deviceList() {
        try {
            System.out.println("Scanning for block devices...");
            ProcessBuilder pb = new ProcessBuilder("lsblk", "-o", "NAME,SIZE,TYPE,RM,MOUNTPOINT");
            Process process = pb.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
            
            System.out.println("Enter the name of your device (e.g., sdb or nvme1n1):");

        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to run lsblk. Do you have it installed.");
        }
    }

	/**
     * Validates a device path (e.g., from a CLI argument).
     * * <p>Checks if the device exists, resolves the real path, and ensures 
     * it isn't currently mounted before giving the green light.</p>
     * * @param deviceName Device name (e.g., "sda" or "/dev/sda")
     * @return Full validated path, or null if it's a bad idea to use it
     */
    public static String validateAndGetPath(String deviceName) {
        if (deviceName == null || deviceName.isBlank()) return null;

        try {
            // Support both "sda" and "/dev/sda"
            String checkPath = deviceName.startsWith("/dev/") ? deviceName : "/dev/" + deviceName;
            Path path = Path.of(checkPath);
            String resolvedPath = path.toRealPath().toString();

            // Safety check for CLI arguments too!
            if (isMounted(resolvedPath)) {
                System.out.println("Hold up! " + resolvedPath + " is mounted. I won't let you flash it like that.");
                return null;
            }

            return resolvedPath;
        } catch (IOException e) {
            System.out.println("Hmm... I couldn't find a device at '" + deviceName + "'. Is it plugged in?");
            return null;
        }
    }
}