package org.cametendo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Scanner;

public class StorageDeviceLister {

    public static String device = "";
    public static String fullPath = "";

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

    public static String validateAndGetPath(String deviceName) {
        try {
            Path path = Path.of("/dev/" + deviceName);
            return path.toRealPath().toString();
        } catch (IOException e) {
            System.out.println("Device not found. Invalid Path or no access.");
            return null;
        }
    }

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