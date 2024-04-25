package ir.msob.jima.core.ral.mongo.it.security;

import ir.msob.jima.core.commons.security.BaseUser;
import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.SortedSet;

/**
 * @author Yaqub Abdi
 */
public class ProjectUser extends BaseUser {
    @Builder
    public ProjectUser(ObjectId id, ObjectId sessionId, String name, String username, SortedSet<String> roles, String audience) {
        super(id.toString(), sessionId.toString(), name, username, roles, audience);
    }

    public ProjectUser() {
        super();
    }
}
