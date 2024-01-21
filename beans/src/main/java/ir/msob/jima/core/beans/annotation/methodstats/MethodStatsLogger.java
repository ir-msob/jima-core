package ir.msob.jima.core.beans.annotation.methodstats;

import ir.msob.jima.core.beans.configuration.JimaConfigProperties;
import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * This class is an Aspect for logging method execution time statistics based on the `MethodStats` annotation.
 * It is marked as a Spring component to be detected during classpath scanning.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class MethodStatsLogger {
    /**
     * Logger instance for logging method execution statistics.
     */
    private static final Logger log = LoggerFactory.getLog(MethodStatsLogger.class);

    // Core configuration properties instance
    private final JimaConfigProperties properties;

    /**
     * This method intercepts methods annotated with `@MethodStats` and logs their execution time.
     * It is marked with the `@Around` annotation to run both before and after the method execution.
     *
     * @param point The method execution join point.
     * @return The result of the method execution.
     * @throws Throwable If an exception occurs during method execution.
     */
    @Around("@annotation(ir.msob.jima.core.commons.annotation.methodstats.MethodStats)")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        // If either warning or info logging is enabled
        if (properties.getMethodStats().isWarnLogEnabled() || properties.getMethodStats().isInfoLogEnabled()) {
            // Record the start time
            long start = System.currentTimeMillis();

            // Proceed with the method execution and capture the result
            Object result = point.proceed();

            // Calculate the time difference
            long dif = System.currentTimeMillis() - start;

            // Prepare the log message
            String msg = String.format("ClassName=%s, MethodName=%s, TimeMS=%d, ThreadName=%s"
                    , point.getSignature().getDeclaringTypeName()
                    , ((MethodSignature) point.getSignature()).getMethod().getName()
                    , dif
                    , Thread.currentThread().getName());

            // If warning logging is enabled and the execution time exceeds the threshold, log a warning
            if (properties.getMethodStats().isWarnLogEnabled() && dif > properties.getMethodStats().getWarnTime())
                log.warn(msg);
                // If info logging is enabled, log an info message
            else if (properties.getMethodStats().isInfoLogEnabled())
                log.info(msg);

            // Return the result of the method execution
            return result;
        } else {
            // If neither warning nor info logging is enabled, proceed with the method execution without logging
            return point.proceed();
        }
    }
}