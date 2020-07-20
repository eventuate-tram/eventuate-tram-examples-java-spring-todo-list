package io.eventuate.tram.examples.todolist.view;


public class TodoView {

  public static final String INDEX = "todos";
  public static final String TYPE = "todo";

  private String id;

  private String title;

  private String completed;

  private String executionOrder;

  public TodoView() {
  }

  public TodoView(String id, String title, String completed, String executionOrder) {
    this.id = id;
    this.title = title;
    this.completed = completed;
    this.executionOrder = executionOrder;
  }

  public TodoView(String id, String title, boolean completed, int executionOrder) {
    this.id = id;
    this.title = title;
    this.completed = String.valueOf(completed);
    this.executionOrder = String.valueOf(executionOrder);
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

  public String isCompleted() {
    return completed;
  }

  public void setCompleted(String completed) {
    this.completed = completed;
  }

  public String getExecutionOrder() {
    return executionOrder;
  }

  public void setExecutionOrder(String executionOrder) {
    this.executionOrder = executionOrder;
  }
}
