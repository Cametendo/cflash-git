package org.cametendo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.FileNameCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

/**
 * Handles file path input and validation for disk image files.
 * 
 * <p>This class provides functionality to interactively prompt users for image file paths
 * with tab completion support, validate that files exist and are regular files, and
 * validate file paths from command-line arguments.</p>
 * 
 * @author Cametendo
 * @version 1.0
 */
public class FilePathAdd {

    /**
     * Stores the validated path to the selected image file.
     */
    public static String ImagePath = "";

    /**
     * Interactively prompts the user to select an image file path.
     * 
     * <p>Uses JLine for enhanced terminal interaction with tab completion support.
     * Validates that the selected path points to an existing regular file.</p>
     * 
     * @return The validated path to the selected image file
     * @throws IOException If there are I/O errors during terminal setup or file validation
     */
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

    /**
     * Validates and returns the full path to an image file.
     * 
     * <p>Takes a file path, validates that it exists and is a regular file,
     * and returns the real path. Used for command-line argument validation.</p>
     * 
     * @param ImagePath Path to the image file to validate
     * @return Full validated path to the file, or null if invalid
     */
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

    /**
     * Displays the prompt for file path input.
     * 
     * <p>Informs the user that they should enter the full path to their ISO/image file
     * and mentions that tab completion is supported for convenience.</p>
     */
    protected static void fileQuestion() {
        System.out.println("Please enter the FULL Path of your ISO / Image. (Tab-completion supported)");
    }
}
