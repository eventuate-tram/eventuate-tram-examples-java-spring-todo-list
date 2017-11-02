package io.eventuate.tram.examples.todolist.view;

import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import io.eventuate.tram.examples.todolist.common.TodoCreated;
import io.eventuate.tram.examples.todolist.common.TodoDeleted;
import io.eventuate.tram.examples.todolist.common.TodoUpdated;
import org.springframework.beans.factory.annotation.Autowired;


public class TodoEventConsumer {

  @Autowired
  private TodoViewService todoViewService;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("net.chrisrichardson.eventstore.examples.tram.todolist.command.Todo")
            .onEvent(TodoCreated.class, todoCreatedDomainEventEnvelope -> {
              TodoCreated todoCreated = todoCreatedDomainEventEnvelope.getEvent();
              todoViewService.index(new TodoView(todoCreatedDomainEventEnvelope.getAggregateId(),
                  todoCreated.getTitle(), todoCreated.isCompleted(), todoCreated.getExecutionOrder()));
            })
            .onEvent(TodoUpdated.class, todoUpdatedDomainEventEnvelope -> {
              TodoUpdated todoUpdated = todoUpdatedDomainEventEnvelope.getEvent();
              todoViewService.index(new TodoView(todoUpdatedDomainEventEnvelope.getAggregateId(),
                  todoUpdated.getTitle(), todoUpdated.isCompleted(), todoUpdated.getExecutionOrder()));
            })
            .onEvent(TodoDeleted.class, todoDeleteDomainEventEnvelope ->
                    todoViewService.remove(todoDeleteDomainEventEnvelope.getAggregateId()))
            .build();
  }
}
