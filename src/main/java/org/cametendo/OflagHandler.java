package org.cametendo;
import java.util.Scanner;

public class OflagHandler {

    public static String oflagHandleString = "direct";

    public static String mapOflagHandle(String input) {
        return switch (input) {
            case "1" -> "direct";
            case "2" -> "dsync";
            case "3" -> "sync";
            case "4" -> "nocache";
            case "5" -> "direct";
            default -> oflagHandleString; 
        };
    }
    
    static String Oflag(Scanner UserInput) {
        System.out.println("Choose an Oflag (Default: direct)");
        System.out.println("direct (1), dsync (2), sync (3), nocache (4)");
        
        String input = UserInput.nextLine();

        oflagHandleString = mapOflagHandle(input);
        
        System.out.println("Using Oflag: " + oflagHandleString);
        return oflagHandleString;
    }
}
