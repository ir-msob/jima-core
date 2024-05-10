package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class MessageProperties {

    private Email email = new Email();
    private Notification notification = new Notification();
    private Integer chunkSize = 50;

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Email {
        private Integer threadPoolSize = 1;
        private String threadNamePrefix = "EmailThreadPoolTaskScheduler";
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Notification {
        private Integer threadPoolSize = 1;
        private String threadNamePrefix = "NotificationThreadPoolTaskScheduler";
    }
}
