package io.eventuate.tram.examples.todolist.command;


import io.eventuate.tram.examples.todolist.common.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    String title = Utils.generateUniqueString();
    Long id = todoCommandService.create(new CreateTodoRequest(title, false, 0)).getId();
    Todo todo = todoRepository.findById(id).orElse(null);
    Assert.assertNotNull(todo);
    Assert.assertEquals(title, todo.getTitle());
  }

  @Test
  public void testUpdate() {
    Todo todo = todoRepository.save(new Todo(Utils.generateUniqueString(), false, 9));
    String title = Utils.generateUniqueString();
    todoCommandService.update(todo.getId(), new UpdateTodoRequest(title, false, 0));
    todo = todoRepository.findById(todo.getId()).orElse(null);
    Assert.assertNotNull(todo);
    Assert.assertEquals(title, todo.getTitle());
  }

  @Test
  public void testDelete() {
    Todo todo = todoRepository.save(new Todo(Utils.generateUniqueString(), false, 9));
    todoCommandService.delete(todo.getId());
    Assert.assertFalse(todoRepository.findById(todo.getId()).isPresent());
  }
}
