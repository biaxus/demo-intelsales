package com.biaxus.core.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, include = "non-lazy")
@Table(name = "AUTHORITY")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
		@AttributeOverride(name = "key", column = @Column(name = "KEY", nullable = false)),
        @AttributeOverride(name = "type", column = @Column(name = "TYPE", nullable = false))
	})
	//@formatter:on
	private AuthorityId id;

	@Column(name = "URL")
	private String url;

	@Column(name = "METHOD")
	@Enumerated(EnumType.STRING)
	private HttpMethod method;

	@Column(name = "STYLE")
	private String style;

	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "SHOW_IN_MENU", nullable=false, columnDefinition="boolean default true")
	private boolean showInMenu;

	@JsonManagedReference("UserAuthority.authority")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
	private Set<UserAuthority> userAutorityList;

	@JsonManagedReference("RoleAuthority.authority")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
	private Set<RoleAuthority> roleAutorityList;

	@JsonManagedReference("RoleEnterpriseAuthority.authority")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
	private Set<EnterpriseRoleAuthority> enterpriseAutorityList;

	//@formatter:off
	@AttributeOverrides({ 
		@AttributeOverride(name = "key", column = @Column(name = "PARENT_KEY")),
        @AttributeOverride(name = "type", column = @Column(name = "PARENT_TYPE"))
	})
	//@formatter:on
	private AuthorityId idParent;

	@JsonBackReference("Authority.sonList")
	@ManyToOne(fetch = FetchType.EAGER)
	// @formatter:off
	@JoinColumns({ 
		@JoinColumn(name = "PARENT_KEY", referencedColumnName = "KEY", insertable = false, updatable = false),
	    @JoinColumn(name = "PARENT_TYPE", referencedColumnName = "TYPE", insertable = false, updatable = false) 
	})
	// @formatter:on
	private Authority parent;

	@JsonManagedReference("Authority.sonList")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<Authority> sonList;

	public Authority() {

	}

	public AuthorityId getId() {
		return id;
	}

	public void setId(AuthorityId id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isShowInMenu() {
		return showInMenu;
	}

	public void setShowInMenu(boolean showInMenu) {
		this.showInMenu = showInMenu;
	}

	public Set<UserAuthority> getUserAutorityList() {
		return userAutorityList;
	}

	public void setUserAutorityList(Set<UserAuthority> userAutorityList) {
		this.userAutorityList = userAutorityList;
	}

	public Set<RoleAuthority> getRoleAutorityList() {
		return roleAutorityList;
	}

	public void setRoleAutorityList(Set<RoleAuthority> roleAutorityList) {
		this.roleAutorityList = roleAutorityList;
	}

	public Set<EnterpriseRoleAuthority> getEnterpriseAutorityList() {
		return enterpriseAutorityList;
	}

	public void setEnterpriseAutorityList(Set<EnterpriseRoleAuthority> enterpriseAutorityList) {
		this.enterpriseAutorityList = enterpriseAutorityList;
	}
	
	public AuthorityId getIdParent() {
		return idParent;
	}

	public void setIdParent(AuthorityId idParent) {
		this.idParent = idParent;
	}

	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}

	public Set<Authority> getSonList() {
		return sonList;
	}

	public void setSonList(Set<Authority> sonList) {
		this.sonList = sonList;
	}

	@Transient
	public String getAuthority() {
		return id.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Authority [id=");
		builder.append(id);
		builder.append(", url=");
		builder.append(url);
		builder.append(", method=");
		builder.append(method);
		builder.append(", style=");
		builder.append(style);
		builder.append(", label=");
		builder.append(label);
		builder.append(", showInMenu=");
		builder.append(showInMenu);
		builder.append(", idParent=");
		builder.append(idParent);
		builder.append(", sonList=");
		builder.append(sonList);
		builder.append("]");
		return builder.toString();
	}
}
