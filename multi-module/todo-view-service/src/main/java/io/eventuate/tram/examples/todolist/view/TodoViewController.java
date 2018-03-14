package io.eventuate.tram.examples.todolist.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/todos")
public class TodoViewController {

  @Autowired
  private TodoViewService todoViewService;

  @RequestMapping(method = RequestMethod.GET)
  public List<TodoView> search(@RequestParam String search) {
    return todoViewService.search(search);
  }
}
