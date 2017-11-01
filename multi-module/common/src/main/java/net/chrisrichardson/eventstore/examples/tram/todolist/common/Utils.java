package net.chrisrichardson.eventstore.examples.tram.todolist.common;


import java.util.UUID;

public class Utils {
  public static String generateUniqueString() {
    return UUID.randomUUID().toString();
  }
}
