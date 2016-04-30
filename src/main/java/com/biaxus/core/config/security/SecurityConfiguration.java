package com.biaxus.core.config.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.biaxus.core.config.filters.CsrfHeaderFilter;
import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.AuthorityType;
import com.biaxus.core.domain.repository.AuthorityRepository;
import com.biaxus.core.security.TokenRepositoryImpl;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenRepositoryImpl tokenRepositoryImpl;
	@Autowired
	private AuthorityRepository authorityRepository;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @formatter:off 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(
						"/login",
						"/logout",
						"/sessionAuthenticationError",
						"/loginFail",
						"/invalidSession",
						"/webjars/**")
					.permitAll()
				.antMatchers("/rest/login")
					.anonymous()
        		.antMatchers("/rest/logout")
					.authenticated();
		List<Authority> authorityList = authorityRepository.findByIdTypeIn(AuthorityType.FUNCTION, AuthorityType.VIEW);
		for (Authority a : authorityList) {
			if(a.getAuthority().startsWith("VIEW_ALL_"))
				continue;
			http.authorizeRequests().antMatchers(a.getMethod(),a.getUrl()).hasAuthority(a.getAuthority());
		}
		http.authorizeRequests()
				.antMatchers("/", "/**")
				.authenticated()
			.and()
				.exceptionHandling()
					.accessDeniedPage("/404?denied")
		    .and()
		        .logout()
		        	.logoutUrl("/rest/logout")
		        	.logoutSuccessUrl("/logout")
		        	.deleteCookies("JSESSIONID")
        	.and()
		        .formLogin()
		        	.loginPage("/login")
		        	.loginProcessingUrl("/rest/login")
		        	.defaultSuccessUrl("/")
		        	.failureHandler(authenticationFailureHandler())
	        .and()
		        .sessionManagement()
		        	.sessionAuthenticationErrorUrl("/sessionAuthenticationError")
		        	.invalidSessionUrl("/invalidSession")
		        	.maximumSessions(-1)
	        		.maxSessionsPreventsLogin(false)
	        		.sessionRegistry(sessionRegistry())
			        .and()
				        .sessionFixation()
				        .newSession()
	        .and()
		        .rememberMe()
			        .key("clave_seguridad_biaxus_porfavor_cambiar")
			        .tokenRepository(tokenRepositoryImpl)
	        .and()
	        	.csrf()
	        		.csrfTokenRepository(csrfTokenRepository())
    		.and()
    			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	// @formatter:on

	// @formatter:off
	@Autowired
	public void configure(AuthenticationManagerBuilder auth, 
						  UserDetailsService userDetailsService, 
						  PasswordEncoder passwordEncoder) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	// @formatter:on

	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		ExceptionMappingAuthenticationFailureHandler authenticationFailureHandler = new ExceptionMappingAuthenticationFailureHandler();
		Map<String, String> map = new HashMap<>();
		map.put(SessionAuthenticationException.class.getName(), "/sessionAuthenticationError");
		authenticationFailureHandler.setExceptionMappings(map);
		authenticationFailureHandler.setDefaultFailureUrl("/loginFail");
		return authenticationFailureHandler;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
}