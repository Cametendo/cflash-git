import java.util.Scanner;

public class FilePathAdd {

    public static String ImagePath = "";

    protected static String filePath(Scanner UserInput) {
        System.out.println("Please enter the FULL Path of your ISO / Image. ()");
        ImagePath = UserInput.nextLine();
        System.out.println("Using File: " + ImagePath);
        return ImagePath;
    }
}
