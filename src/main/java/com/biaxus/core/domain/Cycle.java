package com.biaxus.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "CYCLE")
public class Cycle implements Serializable {

	private static final long serialVersionUID = 325394849874671968L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCIPTION")
	private String description;

	@JsonBackReference("Enterprise.cycleList")
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "cycleList")
	private Set<Enterprise> enterpriseList;

	protected Cycle() {

	}

	public Cycle(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Enterprise> getEnterpriseList() {
		return enterpriseList;
	}

	public void setEnterpriseList(Set<Enterprise> enterpriseList) {
		this.enterpriseList = enterpriseList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cycle [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
