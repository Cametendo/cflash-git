package org.cametendo;
import java.util.Scanner;

public class BlockSize {
    
    public static String blockSizeString = "";
    
    static String blockSize(Scanner UserInput) {
        System.out.println("Choose a block size (Default: 4M)");
        System.out.println("512KB (1), 1M (2), 2M (3), 4M (4), 8M (5), 16M (6)");
        String blockSizeInput = UserInput.nextLine();

        switch (blockSizeInput) {
            case "1":
                blockSizeString = "512KB";
                break;
            case "2":
                blockSizeString = "1M";
                break;
            case "3":
                blockSizeString = "2M";
                break;
            case "4":
                blockSizeString = "4M";
                break;
            case "5":
                blockSizeString = "8M";
                break;
            case "6":
                blockSizeString = "16M";
                break;  
            default:
                blockSizeString = "4M";
                break;
        }
        System.out.println("Using blocksize of: " + blockSizeString);
        return blockSizeString;
    }
}
