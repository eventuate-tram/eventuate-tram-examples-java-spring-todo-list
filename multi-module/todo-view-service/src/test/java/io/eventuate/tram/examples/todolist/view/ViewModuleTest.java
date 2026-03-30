package io.eventuate.tram.examples.todolist.view;

import io.eventuate.tram.examples.todolist.common.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TodoViewConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class ViewModuleTest {

  @Autowired
  private TodoViewService todoViewService;

  @Test
  public void testIndexSearchAndRemove() throws Exception {
    String id = Utils.generateUniqueString();
    String title = "test";
    TodoView todoView = new TodoView(id, title, false, 0);

    todoViewService.index(todoView);
    Thread.sleep(2000);
    List<TodoView> todoViews = todoViewService.search(title);
    assertTrue(todoViews.stream().anyMatch(view -> id.equals(view.getId())));

    todoViewService.remove(id);
    Thread.sleep(2000);
    todoViews = todoViewService.search(title);
    assertFalse(todoViews.stream().anyMatch(view -> id.equals(view.getId())));
  }
}
