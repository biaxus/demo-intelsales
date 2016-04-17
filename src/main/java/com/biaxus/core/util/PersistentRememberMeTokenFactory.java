package com.biaxus.core.util;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import com.biaxus.core.domain.RememberMeToken;

public class PersistentRememberMeTokenFactory {

	public static PersistentRememberMeToken fromRememberMeToken(RememberMeToken rmt) {
		PersistentRememberMeToken rememberMeToken = new PersistentRememberMeToken(rmt.getUser().getUsername(), rmt.getSeries(), rmt.getTokenValue(),
		        rmt.getDate());
		return rememberMeToken;
	}
}
