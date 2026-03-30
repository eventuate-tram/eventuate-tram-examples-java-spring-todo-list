package io.eventuate.tram.examples.todolist.command;

import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@Import({NoopDuplicateMessageDetector.class})
public class TodoCommandConfiguration {
}
