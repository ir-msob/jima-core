package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties for message settings.
 * This class holds configuration for email and notification settings, including thread pool sizes and names.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class MessageProperties {

    /**
     * Configuration for email settings.
     */
    private Email email = new Email();

    /**
     * Configuration for notification settings.
     */
    private Notification notification = new Notification();

    /**
     * The size of chunks for processing messages.
     * Default value is 50.
     */
    private Integer chunkSize = 50;

    /**
     * Configuration properties for email settings.
     * Includes thread pool size and thread name prefix.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Email {
        /**
         * The size of the thread pool for email processing.
         * Default value is 1.
         */
        private Integer threadPoolSize = 1;

        /**
         * The prefix for the thread name in the email thread pool.
         * Default value is "EmailThreadPoolTaskScheduler".
         */
        private String threadNamePrefix = "EmailThreadPoolTaskScheduler";
    }

    /**
     * Configuration properties for notification settings.
     * Includes thread pool size and thread name prefix.
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Notification {
        /**
         * The size of the thread pool for notification processing.
         * Default value is 1.
         */
        private Integer threadPoolSize = 1;

        /**
         * The prefix for the thread name in the notification thread pool.
         * Default value is "NotificationThreadPoolTaskScheduler".
         */
        private String threadNamePrefix = "NotificationThreadPoolTaskScheduler";
    }
}
