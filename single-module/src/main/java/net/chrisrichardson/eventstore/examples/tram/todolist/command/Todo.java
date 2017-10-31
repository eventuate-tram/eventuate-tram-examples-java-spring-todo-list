package net.chrisrichardson.eventstore.examples.tram.todolist.command;

import javax.persistence.*;

@Entity
@Table(name = "todo")
@Access(AccessType.FIELD)
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private boolean completed;

  private int executionOrder;

  public Todo() {
  }

  public Todo(String title, boolean completed, int executionOrder) {
    this.title = title;
    this.completed = completed;
    this.executionOrder = executionOrder;
  }

  public Long getId() {
    return id;
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
