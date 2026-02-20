package ir.msob.jima.core.ral.mongo.it.testchild;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.msob.jima.core.commons.channel.BaseChannelTypeReference;
import ir.msob.jima.core.commons.channel.ChannelMessage;
import ir.msob.jima.core.commons.channel.message.*;
import ir.msob.jima.core.commons.domain.BaseDtoTypeReference;
import ir.msob.jima.core.commons.shared.ModelType;
import ir.msob.jima.core.commons.shared.PageDto;
import ir.msob.jima.core.it.security.ProjectUser;

import java.lang.reflect.Type;
import java.util.Collection;

public interface TestChildDtoTypeReference
        extends BaseDtoTypeReference<String, TestChildDto, TestChildCriteria>
        , BaseChannelTypeReference<String, ProjectUser, TestChildDto, TestChildCriteria> {

    @Override
    default TypeReference<PageDto<TestChildDto>> getPageDtoReferenceType() {
        return new TypeReference<PageDto<TestChildDto>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<TestChildCriteria> getCriteriaReferenceType() {
        return new TypeReference<TestChildCriteria>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<TestChildDto> getDtoReferenceType() {
        return new TypeReference<TestChildDto>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, CriteriaMessage<String, TestChildCriteria>>> getChannelMessageCriteriaReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, CriteriaMessage<String, TestChildCriteria>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, PageableMessage<String, TestChildCriteria>>> getChannelMessagePageableReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, PageableMessage<String, TestChildCriteria>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, PageMessage<String, TestChildDto>>> getChannelMessagePageReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, PageMessage<String, TestChildDto>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, JsonPatchMessage<String, TestChildCriteria>>> getChannelMessageJsonPatchReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, JsonPatchMessage<String, TestChildCriteria>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, DtoMessage<String, TestChildDto>>> getChannelMessageDtoReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, DtoMessage<String, TestChildDto>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, DtosMessage<String, TestChildDto>>> getChannelMessageDtosReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, DtosMessage<String, TestChildDto>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<Collection<String>> getIdsReferenceType() {
        return new TypeReference<Collection<String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, ModelType>> getChannelMessageModelTypeReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, ModelType>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, IdMessage<String>>> getChannelMessageIdReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, IdMessage<String>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, IdsMessage<String>>> getChannelMessageIdsReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, IdsMessage<String>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, IdJsonPatchMessage<String>>> getChannelMessageIdJsonPatchReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, IdJsonPatchMessage<String>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, LongMessage>> getChannelMessageLongReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, LongMessage>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }
}
