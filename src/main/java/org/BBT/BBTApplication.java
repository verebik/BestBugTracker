package org.BBT;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Servlet;

import org.BBT.service.UserService;
import org.BBT.service.dto.UserDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import com.sun.faces.config.ConfigureListener;

@SpringBootApplication
public class BBTApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BBTApplication.class, args);
    }

    // Faces servlet setup for Spring Boot.
    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }

    @Bean
    public ServletRegistrationBean<Servlet> facesServletRegistration() {
        ServletRegistrationBean<Servlet> registration = new ServletRegistrationBean<>(facesServlet(), "*.xhtml");
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

    @Bean
    public CommandLineRunner createDefaultUsers(UserService userService) {
        return args -> {
            UserDto user1 = new UserDto();
            user1.setUsername("defaultUser1");
            user1.setEmail("defaultUser1@example.com");
            userService.save(user1);

            UserDto user2 = new UserDto();
            user2.setUsername("defaultUser2");
            user2.setEmail("defaultUser2@example.com");
            userService.save(user2);
        };
    }
}