package ir.msob.jima.core.commons.util;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for managing temporary files and directories.
 */
@Getter
public class TempFile {

    public static final String PATH_SEPRATOR = "/";
    // List to keep track of created temporary files.
    private final List<File> files = new ArrayList<>();

    // The temporary directory where files will be created.
    private File tempDirectory = null;

    /**
     * Constructor to create a temporary directory with a given name.
     *
     * @param tempDirectoryName The name of the temporary directory.
     */
    public TempFile(String tempDirectoryName) {
        try {
            // Create a temporary directory based on the provided name.
            this.tempDirectory = Files.createTempDirectory(tempDirectoryName).toFile();
        } catch (IOException e) {
            // If an error occurs during directory creation, clean up and throw an exception.
            deleteFile(tempDirectory);
            throw new CommonRuntimeException(e);
        }
    }

    /**
     * Static method to delete a file if it exists.
     *
     * @param file The file to be deleted.
     */
    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    /**
     * Create a file object within the temporary directory.
     *
     * @param fileName The name of the temporary file to create.
     * @return The created file object .
     */
    @SneakyThrows
    public File createFileObject(String fileName) {
        File file = new File(tempDirectory.getAbsolutePath() + PATH_SEPRATOR + fileName);
        files.add(file);
        return file;
    }

    /**
     * Close the temporary file management, deleting all created files and the temporary directory.
     */
    public void close() {
        // Delete all temporary files.
        files.forEach(TempFile::deleteFile);

        // Delete the temporary directory itself.
        deleteFile(tempDirectory);

        // Reset the directory and clear the list of files.
        tempDirectory = null;
        files.clear();
    }
}
