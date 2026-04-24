package org.cametendo;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main entry point for the cflash application.
 * 
 * <p>cflash is a command-line utility for flashing disk images to storage devices.
 * It supports both interactive mode and command-line argument mode for automation.</p>
 * 
 * <p>In interactive mode, the user is guided through device selection, file path input,
 * block size configuration, and output flag selection. In command-line mode, all parameters
 * can be specified as arguments for automated flashing.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class Main {

    /**
     * Main method that serves as the entry point for the cflash application.
     * 
     * <p>The application can operate in two modes:</p>
     * <ul>
     *   <li>Interactive mode: No arguments provided, user is guided through the process</li>
     *   <li>Command-line mode: Four arguments provided for automated execution</li>
     * </ul>
     * 
     * <p>Command-line arguments format:</p>
     * <ol>
     *   <li>Device name (e.g., "sda" without /dev/ prefix)</li>
     *   <li>Image file path (absolute or relative path to ISO/image file)</li>
     *   <li>Block size (1-6 or 512K,1M,2M,4M,8M,16M)</li>
     *   <li>Output flag (1-4 or direct,dsync,sync,nocache)</li>
     * </ol>
     * 
     * @param args Command-line arguments. If 4 arguments are provided, runs in automated mode.
     *             If no arguments, runs in interactive mode.
     * @throws InterruptedException If the flashing process is interrupted
     * @throws IOException If there are I/O errors during device/file validation
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner UserInput = new Scanner(System.in);
        if (args.length == 4) {
        String validatedDevice = StorageDeviceLister.validateAndGetPath(args[0]);
        if (validatedDevice == null) {
            return;
        }

        String validatedFile = FilePathAdd.validateAndGetFile(args[1]);
        if (validatedFile == null) {
            return;
        }
        
        StorageDeviceLister.fullPath = validatedDevice;
        FilePathAdd.ImagePath = validatedFile;
        BlockSize.blockSizeString = BlockSize.mapBlockSize(args[2]);
        OflagHandler.oflagHandleString = OflagHandler.mapOflagHandle(args[3]);

        } else {
            Greeting.greeting(UserInput);

            StorageDeviceLister.deviceCheck(UserInput);
            FilePathAdd.filePath();
            BlockSize.blockSize(UserInput);
            OflagHandler.Oflag(UserInput);
        }
        Flasher.flasher(UserInput);
    }
}
