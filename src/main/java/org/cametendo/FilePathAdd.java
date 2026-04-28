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
 * * <p>This class provides functionality to interactively prompt users for image file paths
 * with tab completion support via JLine. It handles Unix-style tilde (~) expansion 
 * to the user's home directory and validates that paths point to existing regular files.</p>
 * * @author Cametendo
 * @version 1.1
 */
public class FilePathAdd {

    /**
     * Stores the validated absolute path to the selected image file.
     */
    public static String ImagePath = "";
    
    /**
     * Constant representing the current user's home directory path.
     */
    public static final String Home = System.getProperty("user.home");

    /**
     * Interactively prompts the user to select an image file path.
     * * <p>Uses JLine for enhanced terminal interaction with {@link FileNameCompleter}.
     * The method expands the tilde (~) character if present at the start of the string
     * and validates that the resulting path is a regular file before returning.</p>
     * * @return The validated absolute path to the selected image file
     * @throws IOException If there are I/O errors during terminal setup or file resolution
     */
    protected static String filePath() throws IOException {
        fileQuestion();

        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).completer(new FileNameCompleter()).build();

        while (true) {
            String input = reader.readLine("Path: ").trim();

            if (input.isBlank()) {
                System.out.println("Oops... You didn't specify a file!");
                continue;
            }

            // Expand tilde to the full home directory path
            if (input.startsWith("~")) {
                input = Home + input.substring(1);
            }

            Path path = Path.of(input);

            if (!Files.exists(path) || !Files.isRegularFile(path)) {
                System.out.println("Invalid file! Please ensure the path points to an ISO / image file.");
                continue;
            }

            // Convert to a real, absolute path and return
            ImagePath = path.toRealPath().toString();
            System.out.println("Using File: " + ImagePath);
            return ImagePath;
        }
    }

    /**
     * Validates and returns the full path to an image file from a provided string.
     * * <p>This is primarily used for validating command-line arguments. It supports
     * tilde expansion and verifies file existence and type.</p>
     * * @param inputPath Raw path string to validate
     * @return Full validated absolute path, or {@code null} if the path is invalid or inaccessible
     */
    public static String validateAndGetFile(String inputPath) {
        if (inputPath == null) return null;
        
        try {
            if (inputPath.startsWith("~")) {
                inputPath = Home + inputPath.substring(1);
            }

            Path path = Path.of(inputPath);
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
     * Displays the prompt for file path input to the standard output.
     */
    protected static void fileQuestion() {
        System.out.println("Please enter the FULL Path of your ISO / Image. (Tab-completion supported)");
    }
}