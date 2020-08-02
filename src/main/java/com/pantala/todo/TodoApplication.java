package com.pantala.todo;

import com.pantala.todo.auth.AuthFilter;
import com.pantala.todo.auth.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean authFilterRegistration(JwtService jwtService, Logger logger) {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new AuthFilter(logger, jwtService));
		registration.addUrlPatterns("/tasks");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Logger logger(InjectionPoint injectionPoint){
		return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
	}

	@Bean()
	public PasswordEncoder encoder (){
		return new BCryptPasswordEncoder();
	}
}
