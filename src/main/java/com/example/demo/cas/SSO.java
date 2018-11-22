package com.example.demo.cas;

import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class SSO {
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
