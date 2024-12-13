package ir.msob.jima.core.ral.mongo.it.dto;

import ir.msob.jima.core.ral.mongo.it.domain.ProjectDomainAbstract;
import org.bson.types.ObjectId;

/**
 * @author Yaqub Abdi
 */
public abstract class ProjectDtoAbstract extends ProjectDomainAbstract implements ProjectDto {

    public ProjectDtoAbstract(ObjectId objectId) {
        super(objectId);
    }
}
