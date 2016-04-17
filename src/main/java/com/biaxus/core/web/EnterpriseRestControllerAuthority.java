package com.biaxus.core.web;

import org.springframework.http.HttpMethod;

import com.biaxus.core.domain.AuthorityType;

public enum EnterpriseRestControllerAuthority {
	// @formatter:off
	ENTERPRISE_FIND_ALL(HttpMethod.GET,"/rest/enterprise"),
	ENTERPRISE_FIND_ONE(HttpMethod.GET,"/rest/enterprise/{\\d+}"),
	ENTERPRISE_ADD(HttpMethod.POST,"/rest/enterprise/{\\d+}"),
	ENTERPRISE_UPDATE(HttpMethod.PUT,"/rest/enterprise/{\\d+}"),
	ENTERPRISE_DELETE(HttpMethod.DELETE,"/rest/enterprise/{\\d+}"),
	ENTERPRISE_FIND_BY_CYCLE(HttpMethod.GET,"/rest/enterprise/findWithCycle");
	
	private HttpMethod method;
	private AuthorityType type;
	private String url;
	
	// @formatter:on
	private EnterpriseRestControllerAuthority(HttpMethod method, String url) {
		this(method, url, AuthorityType.FUNCTION);
	}

	private EnterpriseRestControllerAuthority(HttpMethod method, String url, AuthorityType type) {
		this.method = method;
		this.type = type;
		this.url = url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public AuthorityType getType() {
		return type;
	}

	public void setType(AuthorityType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
