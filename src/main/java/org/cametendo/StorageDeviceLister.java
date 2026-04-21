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
        // 1. lsblk wird genau EINMAL aufgerufen
        deviceList();

        // 2. Die Abfrage-Schleife
        while (true) {
            device = UserInput.nextLine();
            if (device.isBlank()) {
                System.out.println("Oops... Device name is empty. Did you missclick?");
                continue;
            }
            Path path = Path.of("/dev/" + device);

            try {
                // Versuche, den echten Pfad zu finden
                fullPath = path.toRealPath().toString();
                
                // Wenn wir hier ankommen, war der Pfad gültig
                System.out.println("Using device: " + fullPath);
                return fullPath; 

            } catch (IOException e) {
                // Fehler-Output, danach springt die Schleife wieder nach oben
                System.out.println("Failed to access device! Invalid path or no access. Please try again.");
            }
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