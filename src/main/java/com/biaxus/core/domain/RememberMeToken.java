package com.biaxus.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Audited
@Table(name = "REMEMBER_ME_TOKEN")
public class RememberMeToken {

	@Id
	@Column(name = "SERIES", nullable = false)
	private String series;
	@Column(name = "TOKEN_VALUE", nullable = false)
	private String tokenValue;
	@Column(name = "DATE", nullable = false)
	private Date date;

	@JsonManagedReference("RememberMeToken.user")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", nullable = false, updatable = false)
	private User user;

	public RememberMeToken() {

	}

	public RememberMeToken(String series, String tokenValue, Date date) {
		this.series = series;
		this.tokenValue = tokenValue;
		this.date = date;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RememberMeToken [series=");
		builder.append(series);
		builder.append(", tokenValue=");
		builder.append(tokenValue);
		builder.append(", date=");
		builder.append(date);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
}
