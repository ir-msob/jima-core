package ir.msob.jima.core.ral.quartz.commons;

import ir.msob.jima.core.commons.logger.Logger;
import ir.msob.jima.core.commons.logger.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration class for setting up Quartz Scheduler in Spring.
 */
@Configuration
@RequiredArgsConstructor
public class QuartzConfiguration {

    private final DataSource dataSource;
    Logger log = LoggerFactory.getLogger(QuartzConfiguration.class);

    /**
     * Create and configure the SchedulerFactoryBean.
     *
     * @param applicationContext The Spring Application Context.
     * @param quartzProperties   The Quartz properties.
     * @return A configured SchedulerFactoryBean instance.
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext, QuartzProperties quartzProperties) {
        log.info("Initializing SchedulerFactoryBean...");
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
