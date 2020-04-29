package com.lowes.ordermanagement.poc.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Profile("!test")
public class SwaggerConfig {

    /*
     * Swagger configuration
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("POC").select()
                .apis(RequestHandlerSelectors.any()).paths(paths()).build().pathMapping("/");
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return or(regex("/v1.*"));
    }

    private ApiInfo apiInfo() {
        @SuppressWarnings("deprecation")
        ApiInfo apiInfo = new ApiInfo("lowes-ordermanagement-poc",
                "This API deals with order management",
                "V1", "Terms of service", "na", "Copyright",
                "API license URL");
        return apiInfo;
    }
}
