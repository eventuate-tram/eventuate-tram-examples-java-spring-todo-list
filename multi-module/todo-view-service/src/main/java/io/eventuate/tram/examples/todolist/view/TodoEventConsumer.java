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
            .forAggregateType("io.eventuate.tram.examples.todolist.command.Todo")
            .onEvent(TodoCreated.class, dee -> {
              TodoCreated todoCreated = dee.getEvent();
              todoViewService.index(new TodoView(dee.getAggregateId(),
                  todoCreated.getTitle(), todoCreated.isCompleted(), todoCreated.getExecutionOrder()));
            })
            .onEvent(TodoUpdated.class, dee -> {
              TodoUpdated todoUpdated = dee.getEvent();
              todoViewService.index(new TodoView(dee.getAggregateId(),
                  todoUpdated.getTitle(), todoUpdated.isCompleted(), todoUpdated.getExecutionOrder()));
            })
            .onEvent(TodoDeleted.class, dee ->
                    todoViewService.remove(dee.getAggregateId()))
            .build();
  }
}
