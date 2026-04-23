package org.cametendo;

import java.util.Scanner;

public class Greeting {
    public static void greeting(Scanner UserInput) {
        System.out.println("Welcome to cflash!");
        System.out.println("Would you like to flash an image (Y/n)");
        String input = UserInput.nextLine();
		if (YesNo.check(input)) {
			System.out.println("Please choose the to be flashed device (f. e. sda)");
		} else {
			System.out.println("Canceling...");
			System.exit(0);
		}

    }
}
