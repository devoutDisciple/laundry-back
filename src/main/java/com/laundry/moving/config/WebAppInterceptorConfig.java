package com.laundry.moving.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebAppInterceptorConfig implements WebMvcConfigurer {


    @Autowired
    private Interceptor Interceptor;

//
//    @Autowired
//    public void setAuthInterceptor(Interceptor Interceptor) {
//        this.Interceptor = Interceptor;
//    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(Interceptor);
        interceptorRegistration.addPathPatterns("/**/*");
        interceptorRegistration.excludePathPatterns("/login");
        interceptorRegistration.excludePathPatterns("/logout");
        interceptorRegistration.excludePathPatterns("/toLogin");
        // 静态资源
        interceptorRegistration.excludePathPatterns("/js/**", "/css/**", "/images/**", "/lib/**",
                "/fonts/**");
        // swagger-ui
        interceptorRegistration.excludePathPatterns("/swagger-resources/**", "/webjars/**",
                "/v2/**", "/swagger-ui.html/**", "/swagger-ui.html#/**");
    }

    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        /*放行swagger*/
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
