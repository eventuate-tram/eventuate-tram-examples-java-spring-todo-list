package io.eventuate.tram.examples.todolist.view;

import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({NoopDuplicateMessageDetector.class})
public class TodoViewConfiguration {

  @Bean
  public TodoEventConsumer todoEventConsumer() {
    return new TodoEventConsumer();
  }

  @Bean
  public DomainEventDispatcher domainEventDispatcher(TodoEventConsumer todoEventConsumer, DomainEventDispatcherFactory domainEventDispatcherFactory) {
    return domainEventDispatcherFactory.make("todoServiceEvents", todoEventConsumer.domainEventHandlers());
  }
}
