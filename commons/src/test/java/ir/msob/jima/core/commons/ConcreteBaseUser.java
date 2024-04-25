package ir.msob.jima.core.commons;

import ir.msob.jima.core.commons.security.BaseUser;

import java.util.SortedSet;

public class ConcreteBaseUser extends BaseUser {
    public ConcreteBaseUser() {
        super();
    }

    public ConcreteBaseUser(String id, String sessionId, String name, String username, SortedSet<String> role, String audience) {
        super(id, sessionId, name, username, role, audience);
    }

}