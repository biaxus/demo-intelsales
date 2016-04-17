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
@Table(name = "ROLE_SETTING")
public class RoleSetting {

	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
        @AttributeOverride(name = "key", column = @Column(name = "KEY", nullable = false)),
        @AttributeOverride(name = "idRole", column = @Column(name = "ID_ROLE", nullable = false))
	})
	//@formatter:on
	private RoleSettingId id;

	@JsonBackReference("RoleSetting.role")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROLE", nullable = false, insertable = false, updatable = false)
	private Role role;

	@Column(name = "VALUE", nullable = false)
	private String value;

	public RoleSetting() {

	}

	public RoleSettingId getId() {
		return id;
	}

	public void setId(RoleSettingId id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		builder.append("RoleSetting [id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
