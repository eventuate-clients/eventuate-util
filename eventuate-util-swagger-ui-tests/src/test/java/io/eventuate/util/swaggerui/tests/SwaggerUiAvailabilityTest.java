package io.eventuate.util.swaggerui.tests;


import io.eventuate.util.test.async.UrlTesting;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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
    UrlTesting.assertUrlStatusIsOk("localhost", port, "/swagger-ui/index.html");
  }

}
