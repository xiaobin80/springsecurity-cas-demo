package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return new org.springframework.security.core.userdetails.User(
				token.getPrincipal().toString(), 
				"", 
				true, //user.getState().equals("Active"),
				true, 
				true, 
				true, 
				grantedAuthorities);
	}
}
