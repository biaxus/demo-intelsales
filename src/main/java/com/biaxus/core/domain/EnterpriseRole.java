package com.biaxus.core.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Audited
@Entity
@Table(name = "ENTERPRISE_ROLE")
public class EnterpriseRole {

	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
		@AttributeOverride(name = "idEnterprise", column = @Column(name = "ID_ENTERPRISE", nullable = false)),
        @AttributeOverride(name = "idRole", column = @Column(name = "ID_ROLE", nullable = false) ) 
	})
	//@formatter:on
	private EnterpriseRoleId id;

	@JsonBackReference("EnterpriseRole.role")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROLE", nullable = false, insertable = false, updatable = false)
	private Role role;

	@JsonBackReference("EnterpriseRole.enterprise")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENTERPRISE", nullable = false, insertable = false, updatable = false)
	private Enterprise enterprise;

	@JsonManagedReference("EnterpriseRoleAuthority.enterpriseRole")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enterpriseRole")
	private Set<EnterpriseRoleAuthority> enterpriseRoleAuthorityList;

	public EnterpriseRole() {

	}

	public EnterpriseRoleId getId() {
		return id;
	}

	public void setId(EnterpriseRoleId id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Set<EnterpriseRoleAuthority> getEnterpriseRoleAuthorityList() {
		return enterpriseRoleAuthorityList;
	}

	public void setEnterpriseRoleAuthorityList(Set<EnterpriseRoleAuthority> enterpriseRoleAuthorityList) {
		this.enterpriseRoleAuthorityList = enterpriseRoleAuthorityList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseRole [id=");
		builder.append(id);
		builder.append(", role=");
		builder.append(role);
		builder.append(", enterprise=");
		builder.append(enterprise);
		builder.append("]");
		return builder.toString();
	}
}
