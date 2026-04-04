import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dd {
    public static void dd() {
                try {
            ProcessBuilder pb = new ProcessBuilder("sudo", "dd", "if=" + FilePathAdd.ImagePath, "of=" + StorageDeviceLister.fullPath, "bs=" + BlockSize.blockSizeString, "status=progress", "oflag=" + OflagHandler.oflagHandleString);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            
            int character;
            while ((character = reader.read()) != -1) {
                char c = (char) character;
                System.out.print(c);
                System.out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
