package ir.msob.jima.core.ral.mongo.it.security;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.core.commons.security.ClaimKey;
import ir.msob.jima.core.commons.security.ClaimKeyValue;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeSet;


/**
 * @author Yaqub Abdi
 */

public interface BaseMongoProjectUserService extends BaseUserService {
    ProjectUser SYSTEM_USER = ProjectUser.builder()
            .id("000000000000000000000000")
            .sessionId(new ObjectId().toString())
            .username("system")
            .roles(Sets.newTreeSet(Collections.singleton(Roles.ADMIN)))
            .audience(ClaimKeyValue.AUDIENCE_WEB)
            .build();


    @Override
    default <USER extends BaseUser> USER getUser(Map<String, Object> claims) {
        return (USER) ProjectUser.builder()
                .id(String.valueOf(claims.get(ClaimKey.ID)))
                .sessionId(String.valueOf(claims.get(ClaimKey.SESSION_ID)))
                .username(String.valueOf(claims.get(ClaimKey.SUBJECT)))
                .audience(String.valueOf(claims.get(ClaimKey.AUDIENCE)))
                .roles(new TreeSet<>((Collection<String>) claims.get(ClaimKey.ROLES)))
                .build();
    }


    @Override
    default <USER extends BaseUser> USER getSystemUser() {
        if (SYSTEM_USER != null)
            return (USER) SYSTEM_USER;
        return null;
    }
}
