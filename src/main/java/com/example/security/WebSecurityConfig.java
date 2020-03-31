package com.example.security;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.example.config.AppConfigProperties;
import com.example.filters.HttpTracingRequestFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	AppConfigProperties config;
	
	@Autowired
	HttpTracingRequestFilter filter;
	
	@Bean
	OidcClientInitiatedLogoutSuccessHandler logOutHandler() {
		OidcClientInitiatedLogoutSuccessHandler handler = new OidcClientInitiatedLogoutSuccessHandler(
				clientRegistrationRepository);
		handler.setPostLogoutRedirectUri(URI.create(config.getOidcRedirectUri()));
		return handler;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off		
		http.addFilterAfter(filter, FilterSecurityInterceptor.class)
		    .authorizeRequests()		    
		    .antMatchers("/").permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .logout().logoutSuccessUrl("/")
		    .and()
		    .logout().logoutSuccessHandler(logOutHandler())		    
		    .and()
		    .oauth2Client()
		    .and()
		    .oauth2Login();
		// @formatter:on
		
	}

}
