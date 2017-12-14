package io.eventuate.tram.examples.todolist.command;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.examples.todolist.common.TodoCreated;
import io.eventuate.tram.examples.todolist.common.TodoDeleted;
import io.eventuate.tram.examples.todolist.common.TodoUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Arrays.asList;

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

    publishTodoEvent(todo, new TodoCreated(todo.getTitle(), todo.isCompleted(), todo.getExecutionOrder()));

    return todo;
  }

  private void publishTodoEvent(Todo todo, DomainEvent... domainEvents) {
    domainEventPublisher.publish(Todo.class, todo.getId(), asList(domainEvents));
  }

  private void publishTodoEvent(Long id, DomainEvent... domainEvents) {
    domainEventPublisher.publish(Todo.class, id, asList(domainEvents));
  }

  public void update(Long id, UpdateTodoRequest updateTodoRequest) {
    Todo todo = todoRepository.findOne(id);

    if (todo == null) {
      throw new TodoNotFoundException(id);
    }

    todo.setTitle(updateTodoRequest.getTitle());
    todo.setCompleted(updateTodoRequest.isCompleted());
    todo.setExecutionOrder(updateTodoRequest.getOrder());
    todoRepository.save(todo);

    publishTodoEvent(todo, new TodoUpdated(todo.getTitle(), todo.isCompleted(), todo.getExecutionOrder()));
  }

  public void delete(Long id) {
    todoRepository.delete(id);
    publishTodoEvent(id, new TodoDeleted());
  }
}
