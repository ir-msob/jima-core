package ir.msob.jima.core.commons.file;

import ir.msob.jima.core.commons.security.BaseUser;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface BaseFileClient {

    <USER extends BaseUser> Mono<InputStream> get(String filePath, Optional<USER> user);

    <USER extends BaseUser> Mono<String> store(String filePath, String file, Optional<USER> user);

    <USER extends BaseUser> Mono<String> store(String filePath, File file, Optional<USER> user);

    <USER extends BaseUser> Mono<String> store(String filePath, InputStream inputStream, Optional<USER> user);

    <USER extends BaseUser> Mono<Boolean> delete(String filePath, Optional<USER> user);
}
