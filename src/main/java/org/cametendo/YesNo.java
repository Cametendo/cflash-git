package org.cametendo;

/**
 * Utility class for parsing yes/no user responses.
 * 
 * <p>This class provides a simple method to interpret user input as a boolean
 * value, accepting various forms of "yes" and treating everything else as "no".</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class YesNo {
    
    /**
     * Parses user input to determine if it represents a "yes" response.
     * 
     * <p>Accepts "Y", "y", and empty string (default) as yes responses.
     * All other inputs are treated as no responses.</p>
     * 
     * @param input User input string to parse
     * @return true if the input represents a yes response, false otherwise
     */
    public static boolean check(String input) {
        switch (input) {
            case "Y":
                return true;
            case "y":
                return true;
            case "":
                return true;
            case "n":
                return false;
            default:
                return false; 
        }
    }
}
