package net.chrisrichardson.eventstore.examples.tram.todolist;

import net.chrisrichardson.eventstore.examples.tram.todolist.command.TodoCommandConfiguration;
import net.chrisrichardson.eventstore.examples.tram.todolist.common.SwaggerConfiguration;
import net.chrisrichardson.eventstore.examples.tram.todolist.view.TodoViewConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@Import({TodoViewConfiguration.class, TodoCommandConfiguration.class, SwaggerConfiguration.class})
@ComponentScan
public class TodoMain {
  public static void main(String[] args) {
    SpringApplication.run(TodoMain.class, args);
  }
}
