package net.chrisrichardson.eventstore.examples.tram.todolist.view;


import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoCreated;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.TodoUpdated;

public class TodoView {

  public static final String INDEX = "todos";
  public static final String TYPE = "todo";

  private String id;

  private String title;

  private boolean completed;

  private int executionOrder;

  public TodoView() {
  }

  public TodoView(String id, String title, boolean completed, int executionOrder) {
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
