package ir.msob.jima.core.commons.file;

import ir.msob.jima.core.commons.security.BaseUser;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

/**
 * Interface for file operations in the application.
 * Provides methods for retrieving, storing, and deleting files with optional user context.
 */
public interface BaseFileClient {

    /**
     * Retrieves a file as an InputStream from the specified file path.
     *
     * @param filePath the path of the file to retrieve
     * @param user an optional user context for the operation
     * @param <USER> the type of the user
     * @return a Mono emitting the InputStream of the file
     */
    <USER extends BaseUser> Mono<InputStream> get(String filePath, Optional<USER> user);

    /**
     * Stores a file from a string content at the specified file path.
     *
     * @param filePath the path where the file will be stored
     * @param file the string content of the file
     * @param user an optional user context for the operation
     * @param <USER> the type of the user
     * @return a Mono emitting the path of the stored file
     */
    <USER extends BaseUser> Mono<String> store(String filePath, String file, Optional<USER> user);

    /**
     * Stores a file from a File object at the specified file path.
     *
     * @param filePath the path where the file will be stored
     * @param file the File object to store
     * @param user an optional user context for the operation
     * @param <USER> the type of the user
     * @return a Mono emitting the path of the stored file
     */
    <USER extends BaseUser> Mono<String> store(String filePath, File file, Optional<USER> user);

    /**
     * Stores a file from an InputStream at the specified file path.
     *
     * @param filePath the path where the file will be stored
     * @param inputStream the InputStream of the file to store
     * @param user an optional user context for the operation
     * @param <USER> the type of the user
     * @return a Mono emitting the path of the stored file
     */
    <USER extends BaseUser> Mono<String> store(String filePath, InputStream inputStream, Optional<USER> user);

    /**
     * Deletes a file at the specified file path.
     *
     * @param filePath the path of the file to delete
     * @param user an optional user context for the operation
     * @param <USER> the type of the user
     * @return a Mono emitting a boolean indicating whether the deletion was successful
     */
    <USER extends BaseUser> Mono<Boolean> delete(String filePath, Optional<USER> user);
}
