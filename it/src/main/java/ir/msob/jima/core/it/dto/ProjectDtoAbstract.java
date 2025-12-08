package ir.msob.jima.core.it.dto;

import ir.msob.jima.core.it.domain.ProjectDomainAbstract;

/**
 * @author Yaqub Abdi
 */
public abstract class ProjectDtoAbstract extends ProjectDomainAbstract implements ProjectDto {

    public ProjectDtoAbstract(String id) {
        super(id);
    }
}
