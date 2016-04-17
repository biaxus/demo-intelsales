package com.biaxus.core.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.RememberMeToken;
import com.biaxus.core.domain.User;
import com.biaxus.core.domain.repository.RememberMeTokenRepository;
import com.biaxus.core.domain.repository.UserRepository;
import com.biaxus.core.util.PersistentRememberMeTokenFactory;
import com.biaxus.core.util.RememberMeTokenFactory;

@Service
public class TokenRepositoryImpl implements PersistentTokenRepository {

	@Autowired
	private RememberMeTokenRepository rememberMeTokenRepository;
	@Autowired
	private UserRepository userRepository;

	public TokenRepositoryImpl() {

	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		User u = userRepository.findOne(token.getUsername());
		RememberMeToken rmt = RememberMeTokenFactory.fromPersistenTemembermeToken(token);
		rmt.setUser(u);
		rememberMeTokenRepository.save(rmt);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		RememberMeToken rmt = rememberMeTokenRepository.findOne(series);
		if(rmt == null)
			return;
		rmt.setTokenValue(tokenValue);
		rmt.setDate(lastUsed);
		rememberMeTokenRepository.save(rmt);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		RememberMeToken rmt = rememberMeTokenRepository.findBySeriesWithUser(seriesId);
		if(rmt == null)
			return null;
		return PersistentRememberMeTokenFactory.fromRememberMeToken(rmt);
	}

	@Override
	public void removeUserTokens(String username) {
		List<RememberMeToken> rememberMeTokenList = rememberMeTokenRepository.findByUserUsername(username);
		rememberMeTokenRepository.delete(rememberMeTokenList);
	}

}
