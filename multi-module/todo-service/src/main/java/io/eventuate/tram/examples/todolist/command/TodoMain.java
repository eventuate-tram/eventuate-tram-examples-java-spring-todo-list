package io.eventuate.tram.examples.todolist.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({TodoCommandConfiguration.class})
public class TodoMain {
  public static void main(String[] args) {
    SpringApplication.run(TodoMain.class, args);
  }
}
