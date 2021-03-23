package io.eventuate.tram.examples.todolist.view.webapi;


public class TodoViewResponse {
  private String id;
  private String title;

  private boolean completed;

  private int executionOrder;

  public TodoViewResponse() {
  }

  public TodoViewResponse(String id, String title, boolean completed, int executionOrder) {
    this.id = id;
    this.title = title;
    this.completed = completed;
    this.executionOrder = executionOrder;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public int getExecutionOrder() {
    return executionOrder;
  }

  public void setExecutionOrder(int executionOrder) {
    this.executionOrder = executionOrder;
  }
}
