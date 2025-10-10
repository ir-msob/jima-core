package ir.msob.jima.core.commons.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageFileTest {

    private File tempDir;

    @BeforeEach
    void setUp(@TempDir Path tempDir) {
        this.tempDir = tempDir.toFile();
    }

    @Test
    void createImageWithCustomFormatAndDimensions() {
        File outputFile = new File(tempDir, "customImage.png");
        try {
            ImageFile.create(outputFile, "png", 200, 150);
            assertTrue(outputFile.exists());
        } catch (IOException e) {
            fail("Failed to create an image with custom format and dimensions.");
        }
    }

    @Test
    void createImageWithCustomFormatAndDefaultDimensions() {
        File outputFile = new File(tempDir, "customImage.jpg");
        try {
            ImageFile.create(outputFile, "jpg");
            assertTrue(outputFile.exists());
        } catch (IOException e) {
            fail("Failed to create an image with custom format and default dimensions.");
        }
    }

    @Test
    void createImageWithDefaultFormatAndDimensions() {
        File outputFile = new File(tempDir, "defaultImage.jpg");
        try {
            ImageFile.create(outputFile);
            assertTrue(outputFile.exists());
        } catch (IOException e) {
            fail("Failed to create an image with default format and dimensions.");
        }
    }

    @Test
    void createImageWithZeroDimensions() {
        File outputFile = new File(tempDir, "zeroDimensions.jpg");
        assertThrows(IllegalArgumentException.class, () -> ImageFile.create(outputFile, "jpg", 0, 0));
    }

    @Test
    void createImageWithNegativeDimensions() {
        File outputFile = new File(tempDir, "negativeDimensions.jpg");
        assertThrows(IllegalArgumentException.class, () -> ImageFile.create(outputFile, "jpg", -100, -100));
    }

    @Test
    void createImageWithInvalidFile() {
        File outputFile = new File("non_existent_directory/image.jpg");
        assertThrows(IOException.class, () -> ImageFile.create(outputFile));
    }
}
