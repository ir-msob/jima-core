package ir.msob.jima.core.commons.util;

import java.io.*;

/**
 * Utility class for working with text files.
 */
public class TextFile {

    /**
     * The default character to be written to the file if not specified.
     */
    public static final String DEFAULT_CHAR = "A";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private TextFile() {
    }

    /**
     * Writes a specified value to a file for a given number of times.
     *
     * @param file   The file to write to.
     * @param value  The value to be written.
     * @param repeat The number of times the value should be repeated.
     * @throws IOException              if an I/O error occurs during file writing.
     * @throws IllegalArgumentException if the 'repeat' value is not greater than 0.
     */
    public static void write(File file, String value, long repeat) throws IOException {
        if (repeat <= 0) {
            throw new IllegalArgumentException("Repeat value must be greater than 0");
        }

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (int i = 0; i < repeat; i++) {
                bufferedWriter.write(value);
            }
        }
    }

    /**
     * Writes a specified value to a file once.
     *
     * @param file  The file to write to.
     * @param value The value to be written.
     * @throws IOException if an I/O error occurs during file writing.
     */
    public static void write(File file, String value) throws IOException {
        write(file, value, 1L);
    }

    /**
     * Writes a default character to a file for a specified number of times.
     *
     * @param file The file to write to.
     * @param size The number of times the default character should be repeated.
     * @throws IOException              if an I/O error occurs during file writing.
     * @throws IllegalArgumentException if the 'size' value is not greater than 0.
     */
    public static void write(File file, long size) throws IOException {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        write(file, DEFAULT_CHAR, size);
    }

    /**
     * Read the content of a file into a string.
     *
     * @param file The file to read.
     * @return The content of the file as a string.
     * @throws IOException if an I/O error occurs during file reading.
     */
    public static String read(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}
