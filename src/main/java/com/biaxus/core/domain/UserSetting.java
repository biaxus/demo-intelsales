package com.biaxus.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "USER_SETTING")
public class UserSetting {
	
	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
        @AttributeOverride(name = "key", column = @Column(name = "KEY", nullable = false)),
        @AttributeOverride(name = "username", column = @Column(name = "USERNAME", nullable = false))
	})
	//@formatter:on
	private UserSettingId id;
	
	@JsonBackReference("UserSetting.user")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@Column(name = "VALUE", nullable = false)
	private String value;

	public UserSetting() {
		
	}
	
	public UserSettingId getId() {
		return id;
	}

	public void setId(UserSettingId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSetting [id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
