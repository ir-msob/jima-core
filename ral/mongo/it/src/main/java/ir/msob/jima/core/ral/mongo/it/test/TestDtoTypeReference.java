package ir.msob.jima.core.ral.mongo.it.test;

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

public interface TestDtoTypeReference
        extends BaseDtoTypeReference<String, TestDto, TestCriteria>
        , BaseChannelTypeReference<String, ProjectUser, TestDto, TestCriteria> {

    @Override
    default TypeReference<PageDto<TestDto>> getPageDtoReferenceType() {
        return new TypeReference<PageDto<TestDto>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<TestCriteria> getCriteriaReferenceType() {
        return new TypeReference<TestCriteria>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<TestDto> getDtoReferenceType() {
        return new TypeReference<TestDto>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, CriteriaMessage<String, TestCriteria>>> getChannelMessageCriteriaReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, CriteriaMessage<String, TestCriteria>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, PageableMessage<String, TestCriteria>>> getChannelMessagePageableReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, PageableMessage<String, TestCriteria>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, PageMessage<String, TestDto>>> getChannelMessagePageReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, PageMessage<String, TestDto>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, JsonPatchMessage<String, TestCriteria>>> getChannelMessageJsonPatchReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, JsonPatchMessage<String, TestCriteria>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, DtoMessage<String, TestDto>>> getChannelMessageDtoReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, DtoMessage<String, TestDto>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }

    @Override
    default TypeReference<ChannelMessage<ProjectUser, DtosMessage<String, TestDto>>> getChannelMessageDtosReferenceType() {
        return new TypeReference<ChannelMessage<ProjectUser, DtosMessage<String, TestDto>>>() {
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
