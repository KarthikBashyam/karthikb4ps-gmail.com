package com.example.controller;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WelcomeController {
	
	private static final Log LOGGER = LogFactory.getLog(WelcomeController.class);
	
	@GetMapping(path="/")
	public ModelAndView hello() {
		return new ModelAndView("login");
	}
	
	@GetMapping(path="/details")
	public ModelAndView showDetails(OAuth2AuthenticationToken accessToken) {
		OidcUser user = (OidcUser) accessToken.getPrincipal();		
		return new ModelAndView("details", Collections.singletonMap("details", accessToken.getPrincipal().getAttributes()));
	}

}