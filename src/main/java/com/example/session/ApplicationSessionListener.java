package com.example.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;

import com.example.demo.SpringBootOauthSessionExampleApplication;

@Configuration
public class ApplicationSessionListener implements HttpSessionListener {

	private static final Log LOGGER = LogFactory.getLog(SpringBootOauthSessionExampleApplication.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		LOGGER.info("Session Created:" + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOGGER.info("Session Destroyed:" + se.getSession().getId());
	}

}
