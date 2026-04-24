package org.cametendo;
import java.util.Scanner;

/**
 * Handles output flag (oflag) configuration for the dd command.
 * 
 * <p>This class provides functionality to both interactively prompt users for output flag selection
 * and to map command-line inputs to valid oflag values. The default output flag is "direct".</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class OflagHandler {

    /**
     * Default output flag string value.
     * Set to "direct" as the default output flag for dd operations.
     */
    public static String oflagHandleString = "direct";

    /**
     * Maps user input to a valid output flag value.
     * 
     * <p>Supports both numeric inputs (1-4) and string inputs (direct,dsync,sync,nocache).
     * If the input is not recognized, returns the default output flag.</p>
     * 
     * @param input User input string, either numeric (1-4) or output flag string
     * @return Valid output flag string (direct, dsync, sync, nocache, or default)
     */
    public static String mapOflagHandle(String input) {
        return switch (input) {
            case "1", "direct" -> "direct";
            case "2", "dsync" -> "dsync";
            case "3", "sync" -> "sync";
            case "4", "nocache" -> "nocache";
            default -> oflagHandleString; 
        };
    }
    
    /**
     * Interactively prompts the user to select an output flag.
     * 
     * <p>Displays a menu of available output flags and maps the user's input
     * to a valid output flag value using {@link #mapOflagHandle(String)}.</p>
     * 
     * @param UserInput Scanner object for reading user input
     * @return The selected output flag string
     */
    static String Oflag(Scanner UserInput) {
        System.out.println("Choose an Oflag (Default: direct)");
        System.out.println("direct (1), dsync (2), sync (3), nocache (4)");
        
        String input = UserInput.nextLine();

        oflagHandleString = mapOflagHandle(input);
        
        System.out.println("Using Oflag: " + oflagHandleString);
        return oflagHandleString;
    }
}
