package org.cametendo;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner UserInput = new Scanner(System.in);
        if (args.length == 4) {
        String validatedDevice = StorageDeviceLister.validateAndGetPath(args[0]);
        if (validatedDevice == null) {
            return;
        }

        String validatedFile = FilePathAdd.validateAndGetFile(args[1]);
        if (validatedFile == null) {
            return;
        }
        
        StorageDeviceLister.fullPath = validatedDevice;
        FilePathAdd.ImagePath = validatedFile;
        BlockSize.blockSizeString = BlockSize.mapBlockSize(args[2]);
        OflagHandler.oflagHandleString = OflagHandler.mapOflagHandle(args[3]);

        } else {
            Greeting.greeting(UserInput);
            
            StorageDeviceLister.deviceCheck(UserInput);
            FilePathAdd.filePath();
            BlockSize.blockSize(UserInput);
            OflagHandler.Oflag(UserInput);
        }
        Flasher.flasher(UserInput);
    }
}
