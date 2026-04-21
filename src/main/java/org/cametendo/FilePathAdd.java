package org.cametendo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FilePathAdd {

    public static String ImagePath = "";

    protected static String filePath(Scanner UserInput) {
        fileQuestion();
        while (true) {
            ImagePath = UserInput.nextLine();
            if (ImagePath.isBlank()) {
                System.out.println("Oops.. You didn't specify a file, did you missclick?");
                continue;
            }
            Path path = Path.of(ImagePath);
            try {
                Files.readAttributes(path, "basic:size");
                System.out.println("Using File: " + ImagePath);
                return ImagePath;
            } catch (IOException e) {
                System.out.println("Failed to access file, invalid path or no access to file! Please try again.");
            }
        }
    }

    protected static void fileQuestion() {
        System.out.println("Please enter the FULL Path of your ISO / Image. (No tab-complete)");
    }
}
