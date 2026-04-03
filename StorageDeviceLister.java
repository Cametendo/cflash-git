import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StorageDeviceLister {

    public static String device = "";
    public static String fullPath = "";
    
    protected static String deviceList(Scanner UserInput) {

        try {
            ProcessBuilder pb = new ProcessBuilder("lsblk");
            Process process = pb.start();
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String device = UserInput.nextLine();
        System.out.println("Using device: " + "/dev/" + device);
        fullPath = "/dev/" + device;
        return fullPath;
    }
}
