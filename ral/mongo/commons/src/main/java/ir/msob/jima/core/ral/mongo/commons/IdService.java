package ir.msob.jima.core.ral.mongo.commons;

import ir.msob.jima.core.commons.service.BaseIdService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class IdService implements BaseIdService {
    @Override
    public ObjectId newId() {
        return new ObjectId();
    }

    @Override
    public ObjectId of(String id) {
        return new ObjectId(id);
    }
}
