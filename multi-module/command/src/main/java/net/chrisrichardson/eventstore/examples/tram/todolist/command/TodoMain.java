package net.chrisrichardson.eventstore.examples.tram.todolist.command;

import net.chrisrichardson.eventstore.examples.tram.todolist.common.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@Import({TodoCommandConfiguration.class, SwaggerConfiguration.class})
@ComponentScan
public class TodoMain {
  public static void main(String[] args) {
    SpringApplication.run(TodoMain.class, args);
  }
}
