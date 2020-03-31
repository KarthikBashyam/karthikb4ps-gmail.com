package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {

	@Value(value = "oidcRedirectUri")
	String oidcRedirectUri;

	public String getOidcRedirectUri() {
		return oidcRedirectUri;
	}

	public void setOidcRedirectUri(String oidcRedirectUri) {
		this.oidcRedirectUri = oidcRedirectUri;
	}

}
