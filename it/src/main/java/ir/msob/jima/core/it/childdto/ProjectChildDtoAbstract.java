package ir.msob.jima.core.it.childdto;

import ir.msob.jima.core.it.childdomain.ProjectChildDomainAbstract;

/**
 * @author Yaqub Abdi
 */
public abstract class ProjectChildDtoAbstract extends ProjectChildDomainAbstract implements ProjectChildDto {

    public ProjectChildDtoAbstract(String id, String parentId) {
        super(id, parentId);
    }
}
