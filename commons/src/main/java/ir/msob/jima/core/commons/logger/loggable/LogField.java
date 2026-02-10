package ir.msob.jima.core.commons.logger.loggable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogField {
    String name() default "";
    boolean mask() default false;          // mask value
    int maskVisibleChars() default 4;      // visible chars at end when masked
    boolean logSize() default false;      // log size of field (String / Collection / Array). ex: myFieldSize = 10
    boolean logNull() default false;
    int maxLength() default -1; // -1 = unlimited
}
