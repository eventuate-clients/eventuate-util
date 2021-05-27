package io.eventuate.util.spring.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnBean(EventuateSwaggerConfig.class)
public class CommonSwaggerConfiguration {

  @Bean
  public Docket api(EventuateSwaggerConfig eventuateSwaggerConfig,
                    TypeResolver typeResolver) {

    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(eventuateSwaggerConfig.requestHandlerSelectorsBasePackage()))
            .build()
            .pathMapping("/")
            .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false);
  }
}
