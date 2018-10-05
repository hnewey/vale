package org.newdevelopment.vale.config;

import org.jetbrains.annotations.NotNull;
import org.newdevelopment.vale.authorization.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private AuthInterceptor authInterceptor;

    @Autowired
    public WebConfig(@NotNull AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

//    @Bean
//    public FilterRegistrationBean httpRequestContextFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new HttpRequestContextFilter());
//        registration.setName( "Surname Guidance Authenticated REST Endpoints" );
//        registration.addUrlPatterns( "/surname/*", "/log-level/*" );
//        registration.setOrder(0);
//        return registration;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(authInterceptor);
        registration.excludePathPatterns("/healthcheck/**");

    }
}

