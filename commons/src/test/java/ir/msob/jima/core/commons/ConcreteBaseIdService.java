package ir.msob.jima.core.commons;

import ir.msob.jima.core.commons.id.BaseIdService;

import java.io.Serializable;

public class ConcreteBaseIdService implements BaseIdService {
    @Override
    public <ID extends Comparable<ID> & Serializable> ID newId() {
        // You can implement this method based on your application's requirements.
        // For testing purposes, you can return a default value.
        return (ID) "test-id";
    }

    @Override
    public <ID extends Comparable<ID> & Serializable> ID of(String id) {
        return (ID) id;
    }
}