package com.laundry.moving.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebAppInterceptorConfig extends WebMvcConfigurationSupport {


    @Autowired
    private Interceptor interceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns("/login");
        interceptorRegistration.excludePathPatterns("/logout");
        interceptorRegistration.excludePathPatterns("/toLogin");
        interceptorRegistration.excludePathPatterns("/static/**");
        // 静态资源
        interceptorRegistration.excludePathPatterns("/js/**", "/css/**", "/images/**", "/lib/**",
                "/fonts/**");
        // swagger-ui
        interceptorRegistration.excludePathPatterns("/swagger-resources/**", "/webjars/**",
                "/v2/**", "/swagger-ui.html/**", "/swagger-ui.html#/**");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("index");
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
        super.addResourceHandlers(registry);
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
