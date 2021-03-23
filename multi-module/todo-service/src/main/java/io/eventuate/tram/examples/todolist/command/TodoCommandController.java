package io.eventuate.tram.examples.todolist.command;

import io.eventuate.tram.examples.todolist.command.webapi.CreateTodoRequest;
import io.eventuate.tram.examples.todolist.command.webapi.CreateTodoResponse;
import io.eventuate.tram.examples.todolist.command.webapi.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/todos")
public class TodoCommandController {

  @Autowired
  private TodoCommandService todoCommandService;

  @RequestMapping(method = RequestMethod.POST)
  public CreateTodoResponse create(@RequestBody CreateTodoRequest createTodoRequest) {
    Todo todo = todoCommandService.create(createTodoRequest);
    return new CreateTodoResponse(todo.getId());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public void update(@PathVariable Long id, @RequestBody UpdateTodoRequest updateTodoRequest) {
    todoCommandService.update(id, updateTodoRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable Long id) {
    todoCommandService.delete(id);
  }
}
