package net.chrisrichardson.eventstore.examples.tram.todolist.view;

import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import net.chrisrichardson.eventstore.examples.tram.todolist.command.Todo;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoCreated;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoDeleted;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoUpdated;
import org.springframework.beans.factory.annotation.Autowired;


public class TodoEventConsumer {

  @Autowired
  private TodoViewService todoViewService;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType(Todo.class.getName())
            .onEvent(TodoCreated.class, todoCreatedDomainEventEnvelope ->
                    todoViewService.index(new TodoView(todoCreatedDomainEventEnvelope.getEvent())))
            .onEvent(TodoUpdated.class, todoUpdatedDomainEventEnvelope ->
                    todoViewService.index(new TodoView(todoUpdatedDomainEventEnvelope.getEvent())))
            .onEvent(TodoDeleted.class, todoDeleteDomainEventEnvelope ->
                    todoViewService.remove(todoDeleteDomainEventEnvelope.getEvent().getId()))
            .build();
  }
}
