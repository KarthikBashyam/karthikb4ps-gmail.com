package com.example.security;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import com.example.config.AppConfigProperties;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	AppConfigProperties config;
	
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
		http.authorizeRequests()
		    .antMatchers("/").permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .logout().logoutSuccessUrl("/")
		    .and()
		    .logout().logoutSuccessHandler(logOutHandler())
		    .and()
		    .oauth2Login();
		// @formatter:on
		
	}

}
