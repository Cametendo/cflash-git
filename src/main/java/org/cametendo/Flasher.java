package org.cametendo;
import java.util.Scanner;

public class Flasher {
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