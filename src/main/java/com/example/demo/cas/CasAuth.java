package com.example.demo.cas;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.stereotype.Component;

import com.example.demo.security.CasUserDetailsService;

@Component
public class CasAuth {

	@Bean
	public ServiceProperties serviceProperties() {
	  ServiceProperties serviceProperties = new ServiceProperties();
	  serviceProperties.setService("http://localhost:9000/login/cas");
	  serviceProperties.setSendRenew(false);
	  return serviceProperties;
	}
	
	@Bean
	public TicketValidator ticketValidator() {
	  return new Cas30ServiceTicketValidator("https://localhost:8443/cas");
	}
	
	@Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> 
			customUserDetailsService() {
        return new CasUserDetailsService();
    }
	
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
	  CasAuthenticationProvider provider = new CasAuthenticationProvider();
	  
	  provider.setServiceProperties(serviceProperties());
	  provider.setTicketValidator(ticketValidator());
	  provider.setAuthenticationUserDetailsService(customUserDetailsService());
	  provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
	  
	  return provider;
	}
}
