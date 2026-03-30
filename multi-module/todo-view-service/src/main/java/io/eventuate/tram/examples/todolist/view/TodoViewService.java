package io.eventuate.tram.examples.todolist.view;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoViewService {
  @Autowired
  private ElasticsearchClient elasticsearchClient;

  public List<TodoView> search(String value) {
    try {
      boolean indexExists = elasticsearchClient.indices()
              .exists(ExistsRequest.of(e -> e.index(TodoView.INDEX)))
              .value();

      if (!indexExists) {
        return Collections.emptyList();
      }

      SearchResponse<TodoView> response = elasticsearchClient.search(s -> s
                      .index(TodoView.INDEX)
                      .query(q -> q.multiMatch(m -> m.query(value).fields("*"))),
              TodoView.class);

      return response.hits().hits().stream()
              .map(Hit::source)
              .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void index(TodoView todoView) {
    try {
      elasticsearchClient.index(i -> i
              .index(TodoView.INDEX)
              .id(todoView.getId())
              .document(todoView));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void remove(String id) {
    try {
      elasticsearchClient.delete(d -> d
              .index(TodoView.INDEX)
              .id(id));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
