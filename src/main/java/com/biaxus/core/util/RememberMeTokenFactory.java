package com.biaxus.core.util;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import com.biaxus.core.domain.RememberMeToken;

public class RememberMeTokenFactory {

	public static RememberMeToken fromPersistenTemembermeToken(PersistentRememberMeToken rmt) {
		
		RememberMeToken rememberMeToken = new RememberMeToken(rmt.getSeries(), rmt.getTokenValue(), rmt.getDate());
		return rememberMeToken;
	}
}
