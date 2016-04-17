package com.biaxus.core.domain;

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
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "USER_AUTHORITY")
public class UserAuthority {

	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
        @AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false)),
        @AttributeOverride(name = "authorityKey", column = @Column(name = "AUTHORITY_KEY", nullable = false)),
        @AttributeOverride(name = "authorityType", column = @Column(name = "AUTHORITY_TYPE", nullable = false))
	})
	//@formatter:on
	private UserAuthorityId id;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUTHORITY_MODE", nullable = false)
	private AuthorityMode authorityMode;

	@JsonBackReference("UserAuthority.user")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private User user;

	@JsonBackReference("UserAuthority.authority")
	@ManyToOne(fetch = FetchType.LAZY)
	// @formatter:off
	@JoinColumns({ 
		@JoinColumn(name = "AUTHORITY_KEY", referencedColumnName = "KEY", nullable = false, insertable = false, updatable = false),
	    @JoinColumn(name = "AUTHORITY_TYPE", referencedColumnName = "TYPE", nullable = false, insertable = false, updatable = false) 
	})
	// @formatter:on
	private Authority authority;

	public UserAuthority() {

	}

	public UserAuthorityId getId() {
		return id;
	}

	public void setId(UserAuthorityId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public AuthorityMode getAuthorityMode() {
		return authorityMode;
	}

	public void setAuthorityMode(AuthorityMode authorityMode) {
		this.authorityMode = authorityMode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAuthority [id=");
		builder.append(id);
		builder.append(", authorityMode=");
		builder.append(authorityMode);
		builder.append(", user=");
		builder.append(user);
		builder.append(", authority=");
		builder.append(authority);
		builder.append("]");
		return builder.toString();
	}
}
