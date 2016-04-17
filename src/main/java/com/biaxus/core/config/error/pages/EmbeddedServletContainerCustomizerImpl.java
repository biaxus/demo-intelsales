
package com.biaxus.core.config.error.pages;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;

public class EmbeddedServletContainerCustomizerImpl implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
		ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
		ErrorPage invalidRememberMe = new ErrorPage(CookieTheftException.class, "/invalidRememberMe");
		container.addErrorPages(error404Page, error500Page, invalidRememberMe);
	}

}