package ir.msob.jima.core.commons;

import ir.msob.jima.core.commons.security.BaseUser;

import java.util.SortedSet;

public class ConcreteBaseUser extends BaseUser<String> {
    public ConcreteBaseUser() {
        super();
    }

    public ConcreteBaseUser(String id, String sessionId, String username, SortedSet<String> role, String audience) {
        super(id, sessionId, username, role, audience);
    }

}