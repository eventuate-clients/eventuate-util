package io.eventuate.util.test.async;

import org.junit.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlTesting {
  public static void assertUrlStatusIsOk(String host, int port, String path) {
    assertUrlStatusIsOk(String.format("http://%s:%s%s", host, port, path));
  }

  public static void assertUrlStatusIsOk(String url) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      if (connection.getResponseCode() != 200)
        Assert.fail(String.format("Expected 200 for %s, got %s", url, connection.getResponseCode()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
