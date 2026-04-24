package org.cametendo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Handles storage device detection, listing, and validation for the flashing process.
 * 
 * <p>This class provides functionality to list available storage devices using lsblk,
 * validate device paths, and interactively prompt users to select a target device
 * for flashing operations.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class StorageDeviceLister {

    /**
     * Stores the selected device name (without /dev/ prefix).
     */
    public static String device = "";
    
    /**
     * Stores the full validated path to the selected device.
     */
    public static String fullPath = "";

    /**
     * Interactively prompts the user to select a storage device.
     * 
     * <p>Displays a list of available storage devices using the lsblk command,
     * then prompts the user to enter a device name. Validates the device path
     * and continues prompting until a valid device is selected.</p>
     * 
     * @param UserInput Scanner object for reading user input
     * @return The full validated path to the selected device
     */
    protected static String deviceCheck(Scanner UserInput) {
        deviceList();

        while (true) {
            device = UserInput.nextLine();
            if (device.isBlank()) {
                System.out.println("Oops... Device name is empty. Did you missclick?");
                continue;
            }
            Path path = Path.of("/dev/" + device);

            try {
                fullPath = path.toRealPath().toString();
                
                System.out.println("Using device: " + fullPath);
                return fullPath; 

            } catch (IOException e) {
                System.out.println("Failed to access device! Invalid path or no access. Please try again.");
            }
        }
    }

    /**
     * Validates and returns the full path to a storage device.
     * 
     * <p>Takes a device name (without /dev/ prefix), constructs the full path,
     * and validates that the device exists and is accessible. Used for command-line
     * argument validation.</p>
     * 
     * @param deviceName Device name without /dev/ prefix (e.g., "sda")
     * @return Full validated path to the device, or null if invalid
     */
    public static String validateAndGetPath(String deviceName) {
        try {
            Path path = Path.of("/dev/" + deviceName);
            return path.toRealPath().toString();
        } catch (IOException e) {
            System.out.println("Device not found. Invalid Path or no access.");
            return null;
        }
    }

    /**
     * Displays a list of available storage devices using the lsblk command.
     * 
     * <p>Executes the lsblk command to show block devices and their properties,
     * then prompts the user to enter a device name for selection.</p>
     */
    private static void deviceList() {
        try {
            ProcessBuilder pb = new ProcessBuilder("lsblk");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
            System.out.println("Please enter the name of your device (without /dev/):");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}