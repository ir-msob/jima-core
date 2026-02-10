package ir.msob.jima.core.commons.logger.loggable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogInclude {
}