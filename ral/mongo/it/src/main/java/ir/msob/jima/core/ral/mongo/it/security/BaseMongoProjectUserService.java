package ir.msob.jima.core.ral.mongo.it.security;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.core.commons.security.ClaimKey;
import ir.msob.jima.core.commons.security.ClaimKeyValue;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.*;


/**
 * @author Yaqub Abdi
 */

public interface BaseMongoProjectUserService extends BaseUserService {
    ProjectUser SYSTEM_USER = ProjectUser.builder()
            .id(new ObjectId("000000000000000000000000"))
            .sessionId(new ObjectId())
            .username("system")
            .roles(Sets.newTreeSet(Collections.singleton(Roles.ADMIN)))
            .audience(ClaimKeyValue.AUDIENCE_WEB)
            .build();


    @Override
    default <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Optional<USER> getUser(Map<String, Object> claims) {
        return Optional.of((USER) ProjectUser.builder()
                .id(new ObjectId(String.valueOf(claims.get(ClaimKey.ID))))
                .sessionId(new ObjectId(String.valueOf(claims.get(ClaimKey.SESSION_ID))))
                .username(String.valueOf(claims.get(ClaimKey.SUBJECT)))
                .audience(String.valueOf(claims.get(ClaimKey.AUDIENCE)))
                .roles(new TreeSet<>((Collection<String>) claims.get(ClaimKey.ROLES)))
                .build());
    }


    @Override
    default <ID extends Comparable<ID> & Serializable, USER extends BaseUser<ID>> Optional<USER> getSystemUser() {
        return (Optional<USER>) Optional.of(SYSTEM_USER);
    }
}
