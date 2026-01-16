package ir.msob.jima.core.commons.condition;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ReactiveOrNoneCondition implements Condition {

    @Override
    public boolean matches(ConditionContext ctx, @NonNull AnnotatedTypeMetadata md) {

        String type = ctx.getEnvironment()
                .getProperty("spring.main.web-application-type", "servlet");

        return "reactive".equalsIgnoreCase(type)
                || "none".equalsIgnoreCase(type);
    }
}
