package net.chrisrichardson.eventstore.examples.tram.todolist.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TodoViewService {
  @Autowired
  private TransportClient transportClient;

  private ObjectMapper objectMapper = new ObjectMapper();

  public List<TodoView> search(String value) {

    if (!transportClient.admin().indices().prepareExists(TodoView.INDEX).execute().actionGet().isExists()) {
      return Collections.emptyList();
    }

    SearchResponse response = transportClient.prepareSearch(TodoView.INDEX)
            .setTypes(TodoView.TYPE)
            .setQuery(QueryBuilders.termQuery("_all", value))
            .get();

    List<TodoView> result = new ArrayList<>();

    for (SearchHit searchHit : response.getHits()) {
      try {
        result.add(objectMapper.readValue(searchHit.getSourceAsString(), TodoView.class));
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return result;
  }

  public void index(TodoView todoView) {
    try {
      IndexResponse ir = transportClient
          .prepareIndex(TodoView.INDEX, TodoView.TYPE, todoView.getId())
          .setSource(objectMapper.writeValueAsString(todoView), XContentType.JSON)
          .get();
    }
    catch (JsonProcessingException e) {
        throw new RuntimeException(e);
    }
  }

  public void remove(String id) {
    transportClient.prepareDelete(TodoView.INDEX, TodoView.TYPE, id).get();
  }
}
