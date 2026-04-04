import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner UserInput = new Scanner(System.in);
        
        Greeting.greeting();
        String input = UserInput.nextLine();
        
        if (YesNo.check(input)) {
            System.out.println("Please choose the to be flashed device (f. e. sda)");
        } else {
            System.out.println("Canceling...");
            System.exit(0);
        }
        StorageDeviceLister.deviceList(UserInput);
        FilePathAdd.filePath(UserInput);
        BlockSize.blockSize(UserInput);
        OflagHandler.handleOflag(UserInput);
        Flasher.flasher(UserInput);

        
    }
}
