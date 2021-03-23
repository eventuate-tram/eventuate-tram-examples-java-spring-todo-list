package io.eventuate.tram.examples.todolist.view;

import io.eventuate.tram.examples.todolist.common.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@Import({TodoViewConfiguration.class, SwaggerConfiguration.class})
@ComponentScan
public class TodoMain {
  public static void main(String[] args) {
    SpringApplication.run(TodoMain.class, args);
  }
}
