package io.joonhak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Bean
	public RemoteTokenServices tokenServices() {
		var tokenService = new RemoteTokenServices();
		// Can setup at application.properties ( or yml ) too
		tokenService.setCheckTokenEndpointUrl( "http://localhost:8001/oauth/check_token" );
		tokenService.setClientId("client");
		tokenService.setClientSecret("passwd");
		return tokenService;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/post/**").access("#oauth2.hasScope('read')")
					.anyRequest().authenticated();
	}
	
}
