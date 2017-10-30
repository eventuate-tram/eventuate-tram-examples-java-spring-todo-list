package net.chrisrichardson.eventstore.examples.tram.todolist.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TodoViewService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private TransportClient transportClient;

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
        result.add(new ObjectMapper().readValue(searchHit.getSourceAsString(), TodoView.class));
      }
      catch (IOException e) {
          logger.error(e.getMessage(), e);
      }
    }

    return result;
  }

  public void index(TodoView todoView) {
    try {
      IndexResponse ir = transportClient
          .prepareIndex(TodoView.INDEX, TodoView.TYPE, todoView.getId().toString())
          .setSource(new ObjectMapper().writeValueAsString(todoView), XContentType.JSON)
          .get();
    }
    catch (JsonProcessingException e) {
      logger.error(e.getMessage(), e);
    }
  }

  public void remove(Long id) {
    transportClient.prepareDelete(TodoView.INDEX, TodoView.TYPE, id.toString()).get();
  }
}
