package org.cametendo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Executes the dd command for disk flashing operations.
 * 
 * <p>This class handles the actual execution of the dd command with the configured
 * parameters (input file, output device, block size, and output flags). It provides
 * real-time output streaming and calls the OSDetector for completion messages.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class Dd {
    
    /**
     * Executes the dd command with the configured parameters.
     * 
     * <p>Runs the dd command using sudo with the specified image file, target device,
     * block size, and output flags. Streams the command output in real-time to show
     * progress. Upon completion, calls {@link OSDetector#wishWell(String)} to display
     * a completion message based on the image file name.</p>
     */
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
            OSDetector.wishWell(FilePathAdd.ImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
