package io.eventuate.tram.examples.todolist.common;

import io.eventuate.tram.events.common.DomainEvent;

public class TodoUpdated implements DomainEvent {

  private String title;

  private boolean completed;

  private int executionOrder;

  public TodoUpdated() {
  }

  public TodoUpdated(String title, boolean completed, int executionOrder) {
    this.title = title;
    this.completed = completed;
    this.executionOrder = executionOrder;
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
