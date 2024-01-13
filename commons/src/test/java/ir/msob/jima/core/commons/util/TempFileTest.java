package ir.msob.jima.core.commons.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TempFileTest {

    private TempFile tempFile;

    @BeforeEach
    void setUp() {
        tempFile = new TempFile("tempTestDir");
    }

    @AfterEach
    void tearDown() {
        tempFile.close();
    }

    @Test
    void testCreateFileObject() {
        File file = tempFile.createFileObject("testFile.txt");
        assertNotNull(file);
        assertFalse(file.exists());
        if (file.exists())
            file.delete();
    }

    @Test
    void testDeleteFile() throws IOException {
        File file = tempFile.createFileObject("testFile.txt");
        TextFile.write(file, 1);
        TempFile.deleteFile(file);
        assertFalse(file.exists());
    }

    @Test
    void testDeleteFileNonExistent() {
        File nonExistentFile = new File("non_existent_file.txt");
        TempFile.deleteFile(nonExistentFile);
        // Deleting a non-existent file should not result in an error.
        assertFalse(nonExistentFile.exists());
    }

    @Test
    void testClose() throws IOException {
        File file1 = tempFile.createFileObject("testFile1.txt");
        File file2 = tempFile.createFileObject("testFile2.txt");
        TextFile.write(file1, 1);
        TextFile.write(file2, 1);

        assertTrue(file1.exists());
        assertTrue(file2.exists());

        tempFile.close();

        assertFalse(file1.exists());
        assertFalse(file2.exists());
        assertNull(tempFile.getTempDirectory());
        assertTrue(tempFile.getFiles().isEmpty());
    }

    @Test
    void testCreateInNonExistentDirectory() throws IOException {
        tempFile.close();

        // Create a TempFile instance with a directory that does not exist.
        tempFile = new TempFile("nonExistentDir");

        File file = tempFile.createFileObject("testFile.txt");
        TextFile.write(file, 1);
        assertTrue(file.exists());
        tempFile.close();
    }
}
