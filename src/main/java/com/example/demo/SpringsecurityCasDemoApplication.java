package com.example.demo;

import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

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


	@Bean
	public SecurityContextLogoutHandler securityContextLogoutHandler() {
	  return new SecurityContextLogoutHandler();
	}

	@Bean
	public LogoutFilter logoutFilter() {
	  LogoutFilter logoutFilter = new LogoutFilter(
	    "https://localhost:8443/cas/logout", securityContextLogoutHandler());
	  logoutFilter.setFilterProcessesUrl("/logout/cas");
	  return logoutFilter;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
	  SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
	  singleSignOutFilter.setCasServerUrlPrefix("https://localhost:8443/cas");
	  singleSignOutFilter.setIgnoreInitConfiguration(true);
	  return singleSignOutFilter;
	}

	@EventListener
	public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event) {
	  return new SingleSignOutHttpSessionListener();
	}
	
}
