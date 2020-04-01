package snorlaxa.com.infosys.personnel.config;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/24 21:57
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("snorlaxa.com.infosys.personnel.system.view.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("企业信息系统——人才信息库")
                .description("人才信息系统api服务")
                .termsOfServiceUrl("http://swagger.io/")
                .contact(new Contact("Snorlaxa", "localhost:5000", "snorlaxa@163.com.cn"))
                .version("1.0")
                .build();

    }

}
