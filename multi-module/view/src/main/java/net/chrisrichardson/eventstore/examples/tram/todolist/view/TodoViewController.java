package net.chrisrichardson.eventstore.examples.tram.todolist.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/todoviews")
public class TodoViewController {

  @Autowired
  private TodoViewService todoViewService;

  @RequestMapping(method = RequestMethod.GET)
  public List<TodoView> search(@RequestParam String searchValue) {
    return todoViewService.search(searchValue);
  }
}
