package snorlaxa.com.infosys.personnel.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage errorPage=new ErrorPage(HttpStatus.FORBIDDEN,"/403");
        ErrorPage errorPage1=new ErrorPage(HttpStatus.NOT_FOUND,"/404");
        ErrorPage errorPage2=new ErrorPage(HttpStatus.BAD_REQUEST,"/error");
        registry.addErrorPages(errorPage1);
        registry.addErrorPages(errorPage);
        registry.addErrorPages(errorPage2);
    }
}
