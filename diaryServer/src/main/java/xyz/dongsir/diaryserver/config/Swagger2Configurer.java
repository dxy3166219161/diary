package xyz.dongsir.diaryserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description: Application
 *
 * @author yangkun
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2020-06-05 15:46    yangkun        2.0.0       To create
 * </p>
 */
@Configuration
@EnableSwagger2
public class Swagger2Configurer {

    List<Parameter> pars = new ArrayList<>();

    private ApiInfo documentApiInfo() {
        return new ApiInfoBuilder().title("api管理中心").description("api管理中心").termsOfServiceUrl("").version("1.0").build();
    }
    @Bean
    @Order(value = 1)
    public Docket documentRestApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket = docket.apiInfo(documentApiInfo());
        Predicate<RequestHandler> basePackage = RequestHandlerSelectors.basePackage("xyz.dongsir.diaryserver");
        ApiSelectorBuilder globalOperationParameters = docket.groupName("api管理中心").select().apis(basePackage);
        docket.globalOperationParameters(pars);
        ApiSelectorBuilder paths = globalOperationParameters.paths(PathSelectors.regex("^(?!/(constants|util)).*"));
        Docket build = paths.build();
        return build;
    }

}
