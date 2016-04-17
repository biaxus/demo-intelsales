package com.biaxus.core.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Audited
@Table(name = "\"USER\"")
public class User implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME", nullable = false)
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
	private boolean accountNonExpired;
	@Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
	private boolean accountNonLocked;
	@Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
	private boolean credentialsNonExpired;
	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@JsonManagedReference("User.enterprise")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENTERPRISE", nullable = false, updatable = false)
	private Enterprise enterprise;

	@JsonManagedReference("UserAuthority.user")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserAuthority> userAuthorityList;

	@JsonBackReference("RememberMeToken.user")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<RememberMeToken> rememberMeTokenList;

	@JsonManagedReference("User.roleList")
	// @formatter:off
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "\"USER_ROLE\"", 
			joinColumns =  
				@JoinColumn(name = "ID_USER", referencedColumnName = "USERNAME"), 
			inverseJoinColumns = 
				@JoinColumn(name = "ID_ROLE", referencedColumnName = "ID"))
	// @formatter:on 
	private Set<Role> roleList;

	@JsonManagedReference("UserSetting.user")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserSetting> userSettings;

	@Transient
	private Set<GrantedAuthority> authorities;

	@Transient
	private Map<Setting, String> settings;

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Set<UserAuthority> getUserAuthorityList() {
		return userAuthorityList;
	}

	public void setUserAuthorityList(Set<UserAuthority> userAuthorityList) {
		this.userAuthorityList = userAuthorityList;
	}

	public Set<RememberMeToken> getRememberMeTokenList() {
		return rememberMeTokenList;
	}

	public void setRememberMeTokenList(Set<RememberMeToken> rememberMeTokenList) {
		this.rememberMeTokenList = rememberMeTokenList;
	}

	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	public Set<UserSetting> getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(Set<UserSetting> userSettings) {
		this.userSettings = userSettings;
	}

	public Map<Setting, String> getSettings() {
		if (this.settings != null)
			return this.settings;
		Map <Setting, String> settings = new HashMap<>();
 		this.enterprise.getEnterpiseSettings().forEach((setting) -> {
 			settings.put(setting.getId().getKey(), setting.getValue());
		});
		this.roleList.forEach((role) -> {
			role.getRoleSettings().forEach((setting) -> {
	 			settings.put(setting.getId().getKey(), setting.getValue());
			});
		});
		this.getUserSettings().forEach((setting) -> {
 			settings.put(setting.getId().getKey(), setting.getValue());
		});
		this.settings = settings;		
		return this.settings;
	}

	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		if (this.authorities != null)
			return this.authorities;
		Set<GrantedAuthority> authorities = new HashSet<>();
		this.roleList.forEach((v) -> {
			v.getRoleAuthorityList().forEach((v_) -> {
				if (v_.getAuthorityMode().equals(AuthorityMode.GRANT))
					authorities.add(v_.getAuthority());
			});
		});

		this.enterprise.getRoleList().forEach((v) -> {
			v.getEnterpriseRoleAuthorityList().forEach((v_) -> {
				boolean containAutority = authorities.contains(v_.getAuthority());
				if (!containAutority && v_.getAuthorityMode().equals(AuthorityMode.GRANT))
					authorities.add(v_.getAuthority());
				else if (containAutority && v_.getAuthorityMode().equals(AuthorityMode.REVOKE))
					authorities.remove(v_.getAuthority());
			});
		});

		this.userAuthorityList.forEach((v_) -> {
			boolean containAutority = authorities.contains(v_.getAuthority());
			if (!containAutority && v_.getAuthorityMode().equals(AuthorityMode.GRANT))
				authorities.add(v_.getAuthority());
			else if (containAutority && v_.getAuthorityMode().equals(AuthorityMode.REVOKE))
				authorities.remove(v_.getAuthority());
		});

		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
		return this.authorities;
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			if (g2.getAuthority() == null) {
				return -1;
			}
			if (g1.getAuthority() == null) {
				return 1;
			}
			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof User) {
			return username.equals(((User) rhs).username);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
		if (authorities != null && !authorities.isEmpty()) {
			sb.append("Granted Authorities: ");
			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;
				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

	@Override
	public void eraseCredentials() {
		this.password = "";
	}

}
