package org.cametendo;

import java.util.Scanner;

/**
 * Handles the initial greeting and user confirmation for the cflash application.
 * 
 * <p>This class provides a welcome message and prompts the user to confirm
 * whether they want to proceed with flashing an image to a storage device.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class Greeting {
    
    /**
     * Displays a welcome message and prompts for user confirmation.
     * 
     * <p>Shows the cflash welcome message and asks the user if they want to
     * flash an image. If the user declines, exits the application.</p>
     * 
     * @param UserInput Scanner object for reading user confirmation
     */
    public static void greeting(Scanner UserInput) {
        System.out.println("Welcome to cflash!");
    }
}
