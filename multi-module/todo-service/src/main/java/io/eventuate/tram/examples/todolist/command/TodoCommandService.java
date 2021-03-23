package io.eventuate.tram.examples.todolist.command;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.examples.todolist.command.events.TodoCreated;
import io.eventuate.tram.examples.todolist.command.events.TodoDeleted;
import io.eventuate.tram.examples.todolist.command.events.TodoUpdated;
import io.eventuate.tram.examples.todolist.command.webapi.CreateTodoRequest;
import io.eventuate.tram.examples.todolist.command.webapi.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class TodoCommandService {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private DomainEventPublisher domainEventPublisher;

  public Todo create(CreateTodoRequest createTodoRequest) {
    Todo todo = new Todo(createTodoRequest.getTitle(), createTodoRequest.isCompleted(), createTodoRequest.getOrder());
    todo = todoRepository.save(todo);

    domainEventPublisher.publish(Todo.class,
            todo.getId(),
            Collections.singletonList(new TodoCreated(todo.getTitle(), todo.isCompleted(), todo.getExecutionOrder())));

    return todo;
  }

  public void update(Long id, UpdateTodoRequest updateTodoRequest) {
    Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));

    todo.setTitle(updateTodoRequest.getTitle());
    todo.setCompleted(updateTodoRequest.isCompleted());
    todo.setExecutionOrder(updateTodoRequest.getOrder());
    todoRepository.save(todo);

    domainEventPublisher.publish(Todo.class,
            id,
            Collections.singletonList(new TodoUpdated(todo.getTitle(), todo.isCompleted(), todo.getExecutionOrder())));
  }

  public void delete(Long id) {
    todoRepository.deleteById(id);
    domainEventPublisher.publish(Todo.class, id, Collections.singletonList(new TodoDeleted()));
  }
}
