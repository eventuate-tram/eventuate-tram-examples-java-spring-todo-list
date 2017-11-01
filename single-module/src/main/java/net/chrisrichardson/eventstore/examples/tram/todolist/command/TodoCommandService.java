package net.chrisrichardson.eventstore.examples.tram.todolist.command;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoCreated;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoDeleted;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoUpdated;
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
    Todo todo = todoRepository.findOne(id);

    if (todo == null) {
      throw new TodoNotFoundException(id);
    }

    todo.setTitle(updateTodoRequest.getTitle());
    todo.setCompleted(updateTodoRequest.isCompleted());
    todo.setExecutionOrder(updateTodoRequest.getOrder());
    todoRepository.save(todo);

    domainEventPublisher.publish(Todo.class,
            id,
            Collections.singletonList(new TodoUpdated(todo.getTitle(), todo.isCompleted(), todo.getExecutionOrder())));
  }

  public void delete(Long id) {
    todoRepository.delete(id);
    domainEventPublisher.publish(Todo.class, id, Collections.singletonList(new TodoDeleted()));
  }
}
