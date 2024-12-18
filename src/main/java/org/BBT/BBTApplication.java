package org.BBT;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Servlet;

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
}