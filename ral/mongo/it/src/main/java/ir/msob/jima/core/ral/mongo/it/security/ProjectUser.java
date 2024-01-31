package ir.msob.jima.core.ral.mongo.it.security;

import ir.msob.jima.core.commons.security.BaseUser;
import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.SortedSet;

/**
 * @author Yaqub Abdi
 */
public class ProjectUser extends BaseUser<ObjectId> {
    @Builder
    public ProjectUser(ObjectId id, ObjectId sessionId, String username, SortedSet<String> roles, String audience) {
        super(id, sessionId, username, roles, audience);
    }

    public ProjectUser() {
        super();
    }
}
