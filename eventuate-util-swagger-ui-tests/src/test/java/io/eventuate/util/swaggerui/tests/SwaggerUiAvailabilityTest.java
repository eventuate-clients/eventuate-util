package io.eventuate.util.swaggerui.tests;


import io.eventuate.util.test.async.UrlTesting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

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
    UrlTesting.assertUrlStatusIsOk("http://localhost", port, "swagger-ui/index.html");
  }

}
