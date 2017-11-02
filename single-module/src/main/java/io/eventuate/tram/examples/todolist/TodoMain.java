package io.eventuate.tram.examples.todolist;

import io.eventuate.tram.examples.todolist.command.TodoCommandConfiguration;
import io.eventuate.tram.examples.todolist.common.SwaggerConfiguration;
import io.eventuate.tram.examples.todolist.view.TodoViewConfiguration;
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
