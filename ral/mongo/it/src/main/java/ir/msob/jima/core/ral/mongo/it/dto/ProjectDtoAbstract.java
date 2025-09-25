package ir.msob.jima.core.ral.mongo.it.dto;

import ir.msob.jima.core.ral.mongo.it.domain.ProjectDomainAbstract;

/**
 * @author Yaqub Abdi
 */
public abstract class ProjectDtoAbstract extends ProjectDomainAbstract implements ProjectDto {

    public ProjectDtoAbstract(String id) {
        super(id);
    }
}
