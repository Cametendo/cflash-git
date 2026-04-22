package org.cametendo;

import java.util.Scanner;

public class Greeting {
    public static void greeting(Scanner UserInput) {
        System.out.println("Welcome to cflash!");
        System.out.println("Would you like to flash an image (Y/n)");
        UserInput.nextLine();

    }
}
