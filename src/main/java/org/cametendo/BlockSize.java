package org.cametendo;
import java.util.Scanner;

/**
 * Utility class for managing block size configuration for disk flashing operations.
 * 
 * <p>This class provides methods to both interactively prompt users for block size selection
 * and to map command-line inputs to valid block size values. The default block size is 4M.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class BlockSize {
    
    /**
     * Default block size string value.
     * Set to "4M" as the default block size for flashing operations.
     */
    public static String blockSizeString = "4M";

    /**
     * Maps user input to a valid block size value.
     * 
     * <p>Supports both numeric inputs (1-6) and string inputs (512K,1M,2M,4M,8M,16M).
     * If the input is not recognized, returns the default block size.</p>
     * 
     * @param input User input string, either numeric (1-6) or block size string
     * @return Valid block size string (512K, 1M, 2M, 4M, 8M, 16M, or default)
     */
    public static String mapBlockSize(String input) {
        return switch (input) {
            case "1", "512K" -> "512K";
            case "2", "1M" -> "1M";
            case "3", "2M" -> "2M";
            case "4", "4M" -> "4M";
            case "5", "8M" -> "8M";
            case "6", "16M" -> "16M";
            default -> blockSizeString; 
        };
    }
    
    /**
     * Interactively prompts the user to select a block size.
     * 
     * <p>Displays a menu of available block sizes and maps the user's input
     * to a valid block size value using {@link #mapBlockSize(String)}.</p>
     * 
     * @param UserInput Scanner object for reading user input
     * @return The selected block size string
     */
    static String blockSize(Scanner UserInput) {
        System.out.println("Choose a block size (Default: 4M)");
        System.out.println("512KB (1), 1M (2), 2M (3), 4M (4), 8M (5), 16M (6)");
        
        String input = UserInput.nextLine();

        blockSizeString = mapBlockSize(input);
        
        System.out.println("Using blocksize of: " + blockSizeString);
        return blockSizeString;
    }
}