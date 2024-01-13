package ir.msob.jima.core.commons.exception;

import ir.msob.jima.core.commons.model.dto.ModelType;

public abstract class AbstractExceptionResponse extends ModelType {

    public abstract Integer getStatus();
}
