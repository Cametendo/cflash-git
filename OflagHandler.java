import java.util.Scanner;

public class OflagHandler {
    public static void handleOflag(Scanner UserInput) {
        System.out.println("Okay, next up please define your oflag. If you have the block-size default, you can choose default here aswell");
        System.out.println("Available flags: direct (default), dsync, sync, nocache");
        String oflagHandleInput = UserInput.nextLine();

        String oflagHandleString;
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
    }
}
