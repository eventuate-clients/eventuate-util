package io.eventuate.util.spring.swagger;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnBean(EventuateSwaggerConfig.class)
@Import(CommonSwaggerConfiguration.class)
public class CommonSwaggerAutoConfiguration {

}
