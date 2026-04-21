package org.cametendo;
import java.util.Scanner;

public class OflagHandler {

    public static String oflagHandleString = "";

    static String handleOflag(Scanner UserInput) {
        System.out.println("Okay, next up please define your oflag (Default: direct)");
        System.out.println("Available flags: direct (1), dsync (2), sync (3), nocache (4)");
        String oflagHandleInput = UserInput.nextLine();

        switch (oflagHandleInput) {
            case "1":
                oflagHandleString = "direct";
                break;
            case "2":
                oflagHandleString = "dsync";
                break;
            case "3":
                oflagHandleString = "sync";
                break;
            case "4":
                oflagHandleString = "nocache";
                break; 
            default:
                oflagHandleString = "direct";
                break;
        }
        System.out.println("Using oflag: " + oflagHandleString);
        return oflagHandleString;
    }
}
