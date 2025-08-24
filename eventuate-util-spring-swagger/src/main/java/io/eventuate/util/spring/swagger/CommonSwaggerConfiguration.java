package io.eventuate.util.spring.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
public class CommonSwaggerConfiguration {

  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
            .group("public")
            .packagesToScan(requestHandlerSelectorsBasePackage)
            .build();
  }
}
