package net.iterart.seguiunne.config;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

        WebMvcConfigurer.super.addResourceHandlers(registry);

        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        log.info(resourcePath);

        registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
    }

    // ACCESO DENEGADO
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error_403").setViewName("error_403");
    }
    

//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/uploads/**").addResourceLocations("classpath:/uploads/");
//        //super.addResourceHandlers(registry);
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
//        viewResolver.setViewClass(InternalResourceView.class);
//        return viewResolver;
//    }
}
