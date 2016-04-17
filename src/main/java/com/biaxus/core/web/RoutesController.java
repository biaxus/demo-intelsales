package com.biaxus.core.web;

import java.security.Principal;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.AuthorityType;
import com.biaxus.core.service.AuthorityService;

@RestController
public class RoutesController {

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping("/rest/routes")
	public List<Authority> routes(Principal user) {
		AbstractAuthenticationToken aat = ((AbstractAuthenticationToken) user);
		List<Authority> authorizedViewList = authorityService.findPublicViews();
		authorizedViewList.removeIf(new Predicate<Authority>() {
			public boolean test(Authority t) {
				return t.getAuthority().equals("VIEW_ALL_LOGIN") && aat.isAuthenticated();
			};
		});
		for (GrantedAuthority ga : aat.getAuthorities()) {
			if (!(ga instanceof Authority)) {
				continue;
			}
			Authority a = (Authority) ga;
			if (a.getId().getType().equals(AuthorityType.VIEW)) {
				authorizedViewList.add(a);
			}
		}
		return authorizedViewList;
	}

}
