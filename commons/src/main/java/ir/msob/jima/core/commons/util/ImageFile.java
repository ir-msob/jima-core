package ir.msob.jima.core.commons.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for creating image files with customizable format, width, and height.
 */
public class ImageFile {
    public static final String DEFAULT_FORMAT = "jpg";

    private ImageFile() {
    }

    /**
     * Create an image file with the specified format, width, and height.
     *
     * @param outputFile The file to save the image.
     * @param format     The format of the image (e.g., "jpg", "png").
     * @param width      The width of the image in pixels.
     * @param height     The height of the image in pixels.
     * @throws IOException if there is an issue with file I/O.
     */
    public static void create(File outputFile, String format, int width, int height) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ImageIO.write(image, format, outputFile);
    }

    /**
     * Create an image file with the specified format and default width and height (100x100 pixels).
     *
     * @param outputFile The file to save the image.
     * @param format     The format of the image (e.g., "jpg", "png").
     * @throws IOException if there is an issue with file I/O.
     */
    public static void create(File outputFile, String format) throws IOException {
        create(outputFile, format, 100, 100);
    }

    /**
     * Create an image file with the default format ("jpg") and default width and height (100x100 pixels).
     *
     * @param outputFile The file to save the image.
     * @throws IOException if there is an issue with file I/O.
     */
    public static void create(File outputFile) throws IOException {
        create(outputFile, DEFAULT_FORMAT);
    }
}
