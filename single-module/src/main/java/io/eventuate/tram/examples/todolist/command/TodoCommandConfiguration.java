package io.eventuate.tram.examples.todolist.command;

import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@Import({TramEventsPublisherConfiguration.class, TramMessageProducerJdbcConfiguration.class, NoopDuplicateMessageDetector.class})
public class TodoCommandConfiguration {
}
