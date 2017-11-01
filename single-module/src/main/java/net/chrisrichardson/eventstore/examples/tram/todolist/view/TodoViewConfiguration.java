package net.chrisrichardson.eventstore.examples.tram.todolist.view;

import io.eventuate.tram.consumer.kafka.TramConsumerKafkaConfiguration;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.net.InetAddress;

@Configuration
@Import({TramConsumerKafkaConfiguration.class})
public class TodoViewConfiguration {

  @Value("${elasticsearch.host}")
  private String elasticSearchHost;

  @Value("${elasticsearch.port}")
  private int elasticSearchPort;

  @Bean
  public TodoEventConsumer todoEventConsumer() {
    return new TodoEventConsumer();
  }

  @Bean
  public DomainEventDispatcher domainEventDispatcher(TodoEventConsumer todoEventConsumer, MessageConsumer messageConsumer) {
    return new DomainEventDispatcher("todoServiceEvents", todoEventConsumer.domainEventHandlers(), messageConsumer);
  }

  @Bean
  public TransportClient elasticSearchClient() throws Exception {
    return new PreBuiltTransportClient(Settings.builder().put("client.transport.ignore_cluster_name", true).build())
            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticSearchHost), elasticSearchPort));
  }
}
