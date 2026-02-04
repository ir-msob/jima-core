package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration properties for storage and file uploads.
 * <p>
 * Provides validation settings for uploaded files, including:
 * </p>
 * <ul>
 *   <li>Path-based rules via {@code pathPattern}</li>
 *   <li>Maximum file size per rule via {@code maxFileSize}</li>
 *   <li>Allowed file extensions</li>
 *   <li>Allowed MIME types</li>
 * </ul>
 * <p>
 * You can define multiple {@link FileUpload.ValidationRule} entries and a {@code defaultRule}
 * that applies when no specific {@code pathPattern} matches.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class StorageProperties {

    /**
     * Configuration related to file upload validation.
     */
    private FileUpload fileUpload = new FileUpload();

    /**
     * File upload configuration container.
     * Holds a set of path-specific validation rules and a default rule.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class FileUpload {

        /**
         * Ordered list of specific validation rules.
         * The first rule with a matching {@code pathPattern} applies.
         */
        private List<ValidationRule> validationRules = new ArrayList<>();

        /**
         * Fallback validation rule when no {@code pathPattern} matches.
         */
        private ValidationRule defaultRule = new ValidationRule();

        @Setter
        @Getter
        @NoArgsConstructor
        @ToString
        public static class ValidationRule {
            /**
             * If true, enforces that the file extension in the request path
             * matches the extension of the uploaded file name.
             */
            private Boolean validatePathExtension;

            /**
             * Ant-style path pattern (e.g., "/images/**" or "/docs/*.pdf").
             */
            private String pathPattern;

            /**
             * Maximum allowed file size in bytes. Null means use system default.
             */
            private Long maxFileSize;

            /**
             * Allowed file extensions (without dot), case-insensitive (e.g., "png", "jpg").
             */
            private List<String> allowedExtensions = new ArrayList<>();

            /**
             * Allowed MIME types (e.g., "image/png", "application/pdf").
             */
            private List<String> allowedMimeTypes = new ArrayList<>();
        }
    }

}