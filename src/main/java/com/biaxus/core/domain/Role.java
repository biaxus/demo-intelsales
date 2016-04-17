package com.biaxus.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Audited
@Table(name = "ROLE")
public class Role {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "DESCRIPTION")
	private String description;

	@JsonBackReference("User.roleList")
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleList")
	private Set<User> userList;
	
	@JsonManagedReference("RoleAuthority.role")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<RoleAuthority> roleAuthorityList;
	
	@JsonManagedReference("EnterpriseRole.role")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<EnterpriseRole> enterpisesList;
	
	@JsonManagedReference("RoleSetting.role")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<RoleSetting> roleSettings;
	

	public Role() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}

	public Set<RoleAuthority> getRoleAuthorityList() {
		return roleAuthorityList;
	}

	public void setRoleAuthorityList(Set<RoleAuthority> roleAuthorityList) {
		this.roleAuthorityList = roleAuthorityList;
	}

	public Set<EnterpriseRole> getEnterpisesList() {
		return enterpisesList;
	}

	public void setEnterpisesList(Set<EnterpriseRole> enterpisesList) {
		this.enterpisesList = enterpisesList;
	}
	
	public Set<RoleSetting> getRoleSettings() {
		return roleSettings;
	}

	public void setRoleSettings(Set<RoleSetting> roleSettings) {
		this.roleSettings = roleSettings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
