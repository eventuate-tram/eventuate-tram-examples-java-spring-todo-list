package io.eventuate.tram.examples.todolist.command.webapi;


public class UpdateTodoRequest {
  private String title;
  private boolean completed;
  private int order;

  public UpdateTodoRequest() {
  }

  public UpdateTodoRequest(String title, boolean completed, int order) {
    this.title = title;
    this.completed = completed;
    this.order = order;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
