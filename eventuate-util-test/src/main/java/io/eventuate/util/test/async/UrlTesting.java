package io.eventuate.util.test.async;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlTesting {
  public static void assertUrlStatusIsOk(String host, int port, String path) {
    assertUrlStatusIsOk("http://%s:%s%s".formatted(host, port, path));
  }

  public static void assertUrlStatusIsOk(String url) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      if (connection.getResponseCode() != 200)
        Assertions.fail("Expected 200 for %s, got %s".formatted(url, connection.getResponseCode()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
