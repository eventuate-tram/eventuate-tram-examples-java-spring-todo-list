package net.chrisrichardson.eventstore.examples.tram.todolist.command;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
