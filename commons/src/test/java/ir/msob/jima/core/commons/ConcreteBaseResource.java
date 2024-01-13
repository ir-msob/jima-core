package ir.msob.jima.core.commons;

import ir.msob.jima.core.commons.resource.BaseResource;
import ir.msob.jima.core.commons.security.BaseUserService;

public class ConcreteBaseResource implements BaseResource<String, ConcreteBaseUser> {


    @Override
    public BaseUserService getUserService() {
        return null;
    }
}
