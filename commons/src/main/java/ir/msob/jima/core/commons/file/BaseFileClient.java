package ir.msob.jima.core.commons.file;

import ir.msob.jima.core.commons.security.BaseUser;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Optional;

public interface BaseFileClient {

    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Mono<InputStream> get(String filePath, Optional<USER> user);

    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Mono<String> store(String filePath, String file, Optional<USER> user);

    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Mono<String> store(String filePath, File file, Optional<USER> user);

    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Mono<String> store(String filePath, InputStream inputStream, Optional<USER> user);

    <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Mono<Boolean> delete(String filePath, Optional<USER> user);
}
