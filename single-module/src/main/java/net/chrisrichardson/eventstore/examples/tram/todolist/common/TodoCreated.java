package net.chrisrichardson.eventstore.examples.tram.todolist.common;

import io.eventuate.tram.events.common.DomainEvent;

public class TodoCreated implements DomainEvent {

  private Long id;

  private String title;

  private boolean completed;

  private int executionOrder;

  public TodoCreated() {
  }

  public TodoCreated(Long id, String title, boolean completed, int executionOrder) {
    this.id = id;
    this.title = title;
    this.completed = completed;
    this.executionOrder = executionOrder;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
