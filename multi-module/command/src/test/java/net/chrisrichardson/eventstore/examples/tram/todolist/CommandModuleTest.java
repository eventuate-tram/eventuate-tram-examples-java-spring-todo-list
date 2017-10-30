package net.chrisrichardson.eventstore.examples.tram.todolist;


import net.chrisrichardson.eventstore.examples.tram.todolist.command.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TodoCommandConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class CommandModuleTest {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private TodoCommandService todoCommandService;

  @Test
  public void testCreate() {
    String title = UUID.randomUUID().toString();
    Long id = todoCommandService.create(new CreateTodoRequest(title, false, 0)).getId();
    Todo todo = todoRepository.findOne(id);
    Assert.assertNotNull(todo);
    Assert.assertEquals(title, todo.getTitle());
  }

  @Test
  public void testUpdate() {
    Todo todo = todoRepository.save(new Todo(UUID.randomUUID().toString(), false, 9));
    String title = UUID.randomUUID().toString();
    todoCommandService.update(todo.getId(), new UpdateTodoRequest(title, false, 0));
    todo = todoRepository.findOne(todo.getId());
    Assert.assertNotNull(todo);
    Assert.assertEquals(title, todo.getTitle());
  }

  @Test
  public void testDelete() {
    Todo todo = todoRepository.save(new Todo(UUID.randomUUID().toString(), false, 9));
    todoCommandService.delete(todo.getId());
    Assert.assertNull(todoRepository.findOne(todo.getId()));
  }
}
