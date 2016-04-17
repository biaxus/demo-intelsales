package com.biaxus.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Audited
@Table(name = "ENTERPRISE")
public class Enterprise implements Serializable {

	private static final long serialVersionUID = 6459551747251244030L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "DESCRIPTION")
	private String description;

	@JsonManagedReference("Enterprise.cycleList")
	@ManyToMany(fetch = FetchType.LAZY)
	// @formatter:off
	@JoinTable(
			name = "ENTERPRISE_CYCLE", 
			joinColumns =  @JoinColumn(name = "ID_ENTERPRISE", referencedColumnName = "ID"), 
			inverseJoinColumns = @JoinColumn(name = "ID_CYCLE", referencedColumnName = "ID")
	)
	// @formatter:on
	private Set<Cycle> cycleList;

	@JsonManagedReference("EnterpriseRole.enterprise")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enterprise")
	private Set<EnterpriseRole> roleList;

	@JsonBackReference("User.enterprise")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enterprise")
	private Set<User> userList;

	@JsonBackReference("EnterpriseSetting.enterprise")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enterprise")
	private Set<EnterpriseSetting> enterpiseSettings;

	public Enterprise() {

	}

	public Enterprise(String description) {
		this.description = description;
	}

	public Enterprise(String description, Set<Cycle> cycleList) {
		this.description = description;
		this.cycleList = cycleList;
	}

	public String getId() {
		if (id == null)
			id = description.toUpperCase().replace(' ', '_');
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

	public Set<Cycle> getCycleList() {
		return cycleList;
	}

	public void setCycleList(Set<Cycle> cycleList) {
		this.cycleList = cycleList;
	}

	public Set<EnterpriseRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<EnterpriseRole> roleList) {
		this.roleList = roleList;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}

	public Set<EnterpriseSetting> getEnterpiseSettings() {
		return enterpiseSettings;
	}

	public void setEnterpiseSettings(Set<EnterpriseSetting> enterpiseSettings) {
		this.enterpiseSettings = enterpiseSettings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enterprise [key=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
