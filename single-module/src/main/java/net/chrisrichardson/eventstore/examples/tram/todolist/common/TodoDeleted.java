package net.chrisrichardson.eventstore.examples.tram.todolist.common;

import io.eventuate.tram.events.common.DomainEvent;

public class TodoDeleted implements DomainEvent {

  private Long id;

  public TodoDeleted() {
  }

  public TodoDeleted(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
