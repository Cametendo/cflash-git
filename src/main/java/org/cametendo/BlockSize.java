package org.cametendo;
import java.util.Scanner;

public class BlockSize {
    
    public static String blockSizeString = "4M";

    public static String mapBlockSize(String input) {
        return switch (input) {
            case "1" -> "512K";
            case "2" -> "1M";
            case "3" -> "2M";
            case "4" -> "4M";
            case "5" -> "8M";
            case "6" -> "16M";
            default -> blockSizeString; 
        };
    }
    
    static String blockSize(Scanner UserInput) {
        System.out.println("Choose a block size (Default: 4M)");
        System.out.println("512KB (1), 1M (2), 2M (3), 4M (4), 8M (5), 16M (6)");
        
        String input = UserInput.nextLine();

        blockSizeString = mapBlockSize(input);
        
        System.out.println("Using blocksize of: " + blockSizeString);
        return blockSizeString;
    }
}