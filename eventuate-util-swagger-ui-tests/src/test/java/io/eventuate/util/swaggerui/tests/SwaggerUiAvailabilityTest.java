package io.eventuate.util.swaggerui.tests;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SwaggerUiAvailabilityTest.Config.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaggerUiAvailabilityTest {
  @Configuration
  @EnableAutoConfiguration
  public static class Config {
  }

  @LocalServerPort
  private int port;

  @Test
  public void testSwaggerUiAvailability() throws IOException {
    assertUrlStatusIsOk(String.format("http://%s:%s/swagger-ui/index.html", "localhost", port));
  }

  private void assertUrlStatusIsOk(String url) throws IOException {
    HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();

    Assert.assertEquals(200, connection.getResponseCode());
  }
}
