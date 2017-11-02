package io.eventuate.tram.examples.todolist.endtoend;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.eventuate.util.test.async.Eventually;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.restassured.RestAssured.given;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EndToEndTest.class)
@EnableAutoConfiguration
@ComponentScan
public class EndToEndTest {

  @Value("${todo.service.port}")
  private int todoServicePort;

  @Value("${todo.view.service.port}")
  private int todoViewServicePort;

  @Value("${todo.services.host}")
  private String todoServicesHost;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testCreateAndSearchThenUpdateAndSearchThenDeleteAndSearch() throws Exception {

    final String contentType = "application/json";

    String id = given()
            .contentType(contentType)
            .body(objectMapper.writeValueAsString(new CreateTodoRequest("testcreate", false, 0)))
            .when()
            .post(todoServiceBaseUrl() + "/todos")
            .then()
            .statusCode(200)
            .extract()
            .path("id")
            .toString();

    Eventually.eventually(300, 100, TimeUnit.MILLISECONDS, () -> {
        Assert.assertTrue(search("testcreate").stream().anyMatch(view -> id.equals(view.getId())));
    });


    given()
            .contentType(contentType)
            .body(objectMapper.writeValueAsString(new UpdateTodoRequest("testupdate", false, 0)))
            .when()
            .put(todoServiceBaseUrl() + "/todos/{id}", id)
            .then()
            .statusCode(200);

    Eventually.eventually(300, 100, TimeUnit.MILLISECONDS, () -> {
        Assert.assertTrue(search("testupdate").stream().anyMatch(view -> id.equals(view.getId())));
    });

    given()
            .when()
            .delete(todoServiceBaseUrl() + "/todos/{id}", id)
            .then()
            .statusCode(200);

    Eventually.eventually(300, 100, TimeUnit.MILLISECONDS, () -> {
        Assert.assertFalse(search("testupdate").stream().anyMatch(view -> id.equals(view.getId())));
    });
  }

  private List<TodoView> search(String value) {
    try {
      return Arrays.asList(objectMapper.readValue(given()
              .queryParam("searchValue", value)
              .when()
              .get(todoViewServiceBaseUrl() + "/todoviews")
              .then()
              .statusCode(200)
              .extract()
              .response()
              .asString(), TodoView[].class));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private final String todoViewServiceBaseUrl() {
    return String.format("http://%s:%s", todoServicesHost, todoViewServicePort);
  }

  private final String todoServiceBaseUrl() {
    return String.format("http://%s:%s", todoServicesHost, todoServicePort);
  }
}
