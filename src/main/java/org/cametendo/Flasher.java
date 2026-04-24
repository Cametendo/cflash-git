package org.cametendo;
import java.util.Scanner;

/**
 * Handles the final confirmation and execution of the disk flashing process.
 * 
 * <p>This class displays the current configuration to the user for confirmation
 * before proceeding with the actual flashing operation using the dd command.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class Flasher {
    
    /**
     * Displays the current flashing configuration and prompts for user confirmation.
     * 
     * <p>Shows the device path, image file path, block size, and output flag that will be used
     * for the flashing operation. If the user confirms, proceeds with the flashing process
     * by calling {@link Dd#dd()}. If the user cancels, exits the application.</p>
     * 
     * @param UserInput Scanner object for reading user confirmation
     */
    public static void flasher(Scanner UserInput) {
        
        String input = "";
        
        System.out.println("The program will use the following configuration, do you want to flash with this? (Y/n)");
        System.out.println("    - To be flashed device: " + StorageDeviceLister.fullPath);
        System.out.println("    - To be used path: " + FilePathAdd.ImagePath);
        System.out.println("    - To be used blocksize: " + BlockSize.blockSizeString);
        System.out.println("    - To be used oflag: " + OflagHandler.oflagHandleString);
        input = UserInput.nextLine();
        if (YesNo.check(input)) {
            System.out.println("Starting to flash...");
        } else {
            System.out.println("Canceling...");
            System.exit(0);
        }
        Dd.dd();
    }
}