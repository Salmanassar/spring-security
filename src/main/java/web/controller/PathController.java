package web.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("templates/loginPage");
//        registry.addViewController("/").setViewName("templates/tl/list");
        registry.addViewController("/user/").setViewName("templates/user/userList");
        registry.addViewController("/admin/").setViewName("templates/tl/list");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}