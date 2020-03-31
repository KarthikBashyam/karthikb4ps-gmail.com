package com.example.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.SpringBootOauthSessionExampleApplication;

@Component
public class HttpTracingRequestFilter extends OncePerRequestFilter {

	private static final Log LOGGER = LogFactory.getLog(SpringBootOauthSessionExampleApplication.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getUserPrincipal() != null) {
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) request.getUserPrincipal();
			OidcUser oidcUser = (OidcUser) token.getPrincipal();
			LOGGER.info("Id Token: " + oidcUser.getIdToken().getTokenValue());
		}
		filterChain.doFilter(request, response);
	}

}
