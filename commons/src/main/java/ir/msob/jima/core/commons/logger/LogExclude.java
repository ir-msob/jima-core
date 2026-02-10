package ir.msob.jima.core.commons.logger;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogExclude {
}