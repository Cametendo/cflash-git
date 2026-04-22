package org.cametendo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.FileNameCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class FilePathAdd {

    public static String ImagePath = "";

    protected static String filePath() throws IOException {
        fileQuestion();

        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).completer(new FileNameCompleter()).build();

        while (true) {
            ImagePath = reader.readLine("Path: ").trim();

            if (ImagePath.isBlank()) {
                System.out.println("Oops... You didn't specify a file!");
                continue;
            }

            Path path = Path.of(ImagePath);

            if (!Files.exists(path) || !Files.isRegularFile(path)) {
                System.out.println("Invalid file! Please ensure the path points to an ISO / image file.");
                continue;
            }

            System.out.println("Using File: " + ImagePath);
            return ImagePath;
        }
    }

    public static String validateAndGetFile(String ImagePath) {
        try {
            Path path = Path.of(ImagePath);
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return path.toRealPath().toString();
            } else {
                System.out.println("Invalid file! Please ensure the path points to an ISO / image file.");
                return null;
            }
        } catch (IOException e) {
            System.out.println("File not found! Invalid Path or no access.");
            return null;
        }
    }

    protected static void fileQuestion() {
        System.out.println("Please enter the FULL Path of your ISO / Image. (Tab-completion supported)");
    }
}
