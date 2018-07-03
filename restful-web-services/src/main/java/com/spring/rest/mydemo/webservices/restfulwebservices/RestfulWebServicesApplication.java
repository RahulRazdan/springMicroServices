package com.spring.rest.mydemo.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver getLocaleResolver() {
		AcceptHeaderLocaleResolver locale = new AcceptHeaderLocaleResolver();
		locale.setDefaultLocale(Locale.US);
		return locale;
	}
	
	/* Not required if we have property in application.properties
	 * @Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		bundle.setBasename("messages");
		return bundle;
	}*/
}
