package ir.msob.jima.core.commons.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TextFileTest {

    @Test
    void testWriteFileRepeatedly() throws IOException {
        File file = File.createTempFile("testFile", ".txt");
        TextFile.write(file, "ABC", 3);

        String content = TextFile.read(file);
        assertEquals("ABCABCABC", content);
        if (file.exists())
            file.delete();
    }

    @Test
    void testWriteFileOnce() throws IOException {
        File file = File.createTempFile("testFile", ".txt");
        TextFile.write(file, "Hello");

        String content = TextFile.read(file);
        assertEquals("Hello", content);
        if (file.exists())
            file.delete();
    }

    @Test
    void testWriteDefaultChar() throws IOException {
        File file = File.createTempFile("testFile", ".txt");
        TextFile.write(file, 5);

        String content = TextFile.read(file);
        assertEquals("AAAAA", content);
        if (file.exists())
            file.delete();
    }

    @Test
    void testWriteInvalidRepeatValue() throws IOException {
        File file = File.createTempFile("testFile", ".txt");
        assertThrows(IllegalArgumentException.class, () -> TextFile.write(file, "Value", 0));
        if (file.exists())
            file.delete();
    }

    @Test
    void testWriteDefaultCharInvalidSize() throws IOException {
        File file = File.createTempFile("testFile", ".txt");
        assertThrows(IllegalArgumentException.class, () -> TextFile.write(file, 0));
        if (file.exists())
            file.delete();
    }

    @Test
    void testReadFile() throws IOException {
        // Prepare a temporary file with some content.
        File file = File.createTempFile("testFile", ".txt");
        String expectedContent = "This is a test file content.";
        TextFile.write(file, expectedContent);

        // Read the content of the file.
        String actualContent = TextFile.read(file);

        // Verify that the read content matches the expected content.
        assertEquals(expectedContent, actualContent);
        if (file.exists())
            file.delete();
    }

    @Test
    void testReadEmptyFile() throws IOException {
        // Prepare an empty temporary file.
        File file = File.createTempFile("emptyFile", ".txt");

        // Read the content of the empty file.
        String actualContent = TextFile.read(file);

        // Verify that the read content is an empty string.
        assertTrue(actualContent.isEmpty());
        if (file.exists())
            file.delete();
    }

    @Test
    void testReadNonExistentFile() {
        // Attempt to read a non-existent file.
        File nonExistentFile = new File("non_existent_file.txt");

        // Verify that an IOException is thrown.
        assertThrows(IOException.class, () -> TextFile.read(nonExistentFile));
        if (nonExistentFile.exists())
            nonExistentFile.delete();
    }
}
