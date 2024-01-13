package ir.msob.jima.core.commons.annotation.methodstats;

import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * An Aspect for logging method execution time statistics based on the `MethodStats` annotation.
 */
@Aspect
@Component
public class MethodStatsLogger {
    private static final Logger log = LoggerFactory.getLog(MethodStatsLogger.class);

    @Value("${method-stats.log.warn-time:1000}")
    private long warnTime;
    @Value("${method-stats.log.info:false}")
    private boolean infoLog;
    @Value("${method-stats.log.warn:true}")
    private boolean warnLog;

    /**
     * Intercepts methods annotated with `@MethodStats` and logs their execution time.
     *
     * @param point The method execution join point.
     * @return The result of the method execution.
     * @throws Throwable If an exception occurs during method execution.
     */
    @Around("@annotation(ir.msob.jima.core.commons.annotation.methodstats.MethodStats)")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        if (warnLog || infoLog) {
            long start = System.currentTimeMillis();
            Object result = point.proceed();
            long dif = System.currentTimeMillis() - start;

            String msg = String.format("ClassName=%s, MethodName=%s, TimeMS=%d, ThreadName=%s"
                    , point.getSignature().getDeclaringTypeName()
                    , ((MethodSignature) point.getSignature()).getMethod().getName()
                    , dif
                    , Thread.currentThread().getName());

            if (warnLog && dif > warnTime)
                log.warn(msg);
            else if (infoLog)
                log.info(msg);

            return result;
        } else {
            return point.proceed();
        }
    }
}
