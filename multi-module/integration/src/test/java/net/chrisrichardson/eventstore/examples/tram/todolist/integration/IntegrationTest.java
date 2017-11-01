package net.chrisrichardson.eventstore.examples.tram.todolist.integration;


import net.chrisrichardson.eventstore.examples.tram.todolist.command.CreateTodoRequest;
import net.chrisrichardson.eventstore.examples.tram.todolist.command.TodoCommandConfiguration;
import net.chrisrichardson.eventstore.examples.tram.todolist.command.TodoCommandService;
import net.chrisrichardson.eventstore.examples.tram.todolist.command.UpdateTodoRequest;
import net.chrisrichardson.eventstore.examples.tram.todolist.view.TodoView;
import net.chrisrichardson.eventstore.examples.tram.todolist.view.TodoViewConfiguration;
import net.chrisrichardson.eventstore.examples.tram.todolist.view.TodoViewService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TodoViewConfiguration.class, TodoCommandConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class IntegrationTest {

  @Autowired
  private TodoViewService todoViewService;

  @Autowired
  TodoCommandService todoCommandService;

  @Test
  public void testCreateAndSearchThenUpdateAndSearchThenDeleteAndSearch() throws Exception {
    Long id = todoCommandService.create(new CreateTodoRequest("testcreate", false, 0)).getId();
    Assert.assertTrue(waitSearch("testcreate", id, 1000, 30, true));

    todoCommandService.update(id, new UpdateTodoRequest("testupdate", false, 0));
    Assert.assertTrue(waitSearch("testupdate", id, 1000, 30, true));

    todoCommandService.delete(id);
    Assert.assertTrue(waitSearch("testupdate", id, 1000, 30, false));
  }

  private boolean waitSearch(String value, Long expectedId, int waitTimeInMilliseconds, int maxAttempts, boolean shouldBeFound) throws Exception {
    boolean result = false;
    int attempts = 0;

    while (!result && (attempts <= maxAttempts)) {
      Thread.sleep(waitTimeInMilliseconds);
      List<TodoView> todoViews = todoViewService.search(value);
      result = todoViews.stream().anyMatch(view -> expectedId.toString().equals(view.getId())) == shouldBeFound;
    }

    return result;
  }
}
