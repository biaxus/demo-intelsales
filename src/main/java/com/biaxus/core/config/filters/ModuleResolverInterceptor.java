package com.biaxus.core.config.filters;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.AuthorityType;

@Component
public class ModuleResolverInterceptor implements WebRequestInterceptor {

	private static final Log logger = LogFactory.getLog(ModuleResolverInterceptor.class);

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public void preHandle(WebRequest request) throws Exception {

	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		if (!(request instanceof ServletWebRequest)) {
			logger.warn("The WebRequest recieved is not a ServletWebRequest, recieved " + request.getClass().getCanonicalName());
			return;
		}
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		String requestUri = servletWebRequest.getRequest().getRequestURI();
		logger.info("Detecting current moodules from path: " + requestUri);
		if (servletWebRequest.getUserPrincipal() == null) {
			logger.info("The current principal is null, so there isnt modules to resolve");
			return;
		}
		Principal principal = servletWebRequest.getUserPrincipal();
		if (!(principal instanceof Authentication)) {
			logger.warn("The Principal recieved is not a Authentication, recieved " + principal.getClass().getCanonicalName());
			return;
		}
		Authentication user = (Authentication) principal;

		List<GrantedAuthority> viewList = user.getAuthorities().stream().filter((t) -> {
			if (!(t instanceof Authority)) {
				logger.warn("There is GrantedAuthority not type Authority, recieved " + t.getClass().getCanonicalName());
				return false;
			}
			Authority a = (Authority) t;
			boolean isView = a.getId().getType().equals(AuthorityType.VIEW);
			return isView;
		}).collect(Collectors.toList());

		BiConsumer<List<GrantedAuthority>, GrantedAuthority> accumulator = (gaList, b) -> {
			if (!(b instanceof Authority)) {
				logger.warn("There is GrantedAuthority not type Authority, recieved " + b.getClass().getCanonicalName());
				return;
			}
			Authority auth = (Authority) b;
			@SuppressWarnings({ "rawtypes", "unchecked" })
			BiConsumer<BiConsumer, Authority> factHelper = (f, auth_) -> {
				gaList.add(auth_);
				if (auth_.getParent() == null) {
					return;
				}
				f.accept(f, auth_.getParent());
			};
			factHelper.accept(factHelper, auth);
		};

		BiConsumer<List<GrantedAuthority>, List<GrantedAuthority>> combiner = (a, b) -> {
			a.addAll(b);
		};

		List<GrantedAuthority> currentViewList = viewList.stream().filter((t) -> {
			if (!(t instanceof Authority)) {
				logger.warn("There is GrantedAuthority not type Authority, recieved " + t.getClass().getCanonicalName());
				return false;
			}
			Authority a = (Authority) t;
			String path = requestUri.replaceFirst("/", "");
			String pattern = a.getUrl().replaceFirst("/", "");
			boolean pass = (antPathMatcher.match(pattern, path));
			return pass;
		}).collect(ArrayList::new, accumulator, combiner);

		viewList = viewList.stream().filter((t) -> {
			if (!(t instanceof Authority))
				return false;
			Authority a = (Authority) t;
			boolean isRoot = a.getIdParent() == null;
			return isRoot;
		}).collect(Collectors.toList());
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		BiFunction<BiFunction, Authority, Boolean> recursiveFilter = (fn, _auth)->{
			Set<Authority> sonList = _auth.getSonList().stream().filter((t)->{
				fn.apply(fn, t);
				return t.isShowInMenu();
			}).collect(Collectors.toSet());
			_auth.setSonList(sonList);
			return _auth.isShowInMenu();
		};
		
		viewList = viewList.stream().filter((t) -> {
			if (!(t instanceof Authority))
				return false;
			Authority a = (Authority) t;
			return recursiveFilter.apply(recursiveFilter, a);
		}).collect(Collectors.toList());

		logger.info("Sucessfully viewList resolved: " + viewList);
		logger.info("Sucessfully currentViewList resolved : " + currentViewList);
		model.addAttribute("viewList", viewList);
		model.addAttribute("currentViewList", currentViewList);
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {

	}

}
