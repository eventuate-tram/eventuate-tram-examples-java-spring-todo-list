package net.chrisrichardson.eventstore.examples.tram.todolist;


import net.chrisrichardson.eventstore.examples.tram.todolist.common.Utils;
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
    Thread.sleep(2000); //Elasticsearch by default refreshes each shard every 1s
    List<TodoView> todoViews = todoViewService.search(title);
    Assert.assertTrue(todoViews.stream().anyMatch(view -> id.equals(view.getId())));

    todoViewService.remove(id);
    Thread.sleep(2000);
    todoViews = todoViewService.search(title);
    Assert.assertFalse(todoViews.stream().anyMatch(view -> id.equals(view.getId())));
  }
}
