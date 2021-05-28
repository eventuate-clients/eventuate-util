package io.eventuate.util.spring.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnBean(EventuateSwaggerConfig.class)
@Import(CommonSwaggerConfiguration.class)
public class CommonSwaggerAutoConfiguration {

}
