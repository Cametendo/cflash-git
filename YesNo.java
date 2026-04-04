public class YesNo {
    public static boolean check(String input) {
        switch (input) {
            case "Y":
                return true;
            case "y":
                return true;
            case "":
                return true;
            case "n":
                return false;
            default:
                return false; 
        }
    }
}
