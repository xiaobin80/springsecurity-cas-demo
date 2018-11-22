package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

@SpringBootApplication
public class SpringsecurityCasDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityCasDemoApplication.class, args);
	}
	
	@Bean
	@Primary
	public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sP) {
	  CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
	  entryPoint.setLoginUrl("https://localhost:8443/cas/login");
	  entryPoint.setServiceProperties(sP);
	  return entryPoint;
	}
	
}
