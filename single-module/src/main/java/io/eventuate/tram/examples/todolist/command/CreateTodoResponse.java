package io.eventuate.tram.examples.todolist.command;


public class CreateTodoResponse {
  private Long id;

  public CreateTodoResponse() {
  }

  public CreateTodoResponse(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
