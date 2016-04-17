package com.biaxus.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "DEMO_BILL")
public class DemoBill implements Serializable {

	private static final long serialVersionUID = 325394849874671968L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	private String year_bill; 	 
	private String month_bill;
	private String seller; 
	private String client; 
	private String city_client; 
	private String product; 
	private String presentation; 
	private String unit_value; 
	private String quantity; 
	private String total; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear_bill() {
		return year_bill;
	}

	public void setYear_bill(String year_bill) {
		this.year_bill = year_bill;
	}

	public String getMonth_bill() {
		return month_bill;
	}

	public void setMonth_bill(String month_bill) {
		this.month_bill = month_bill;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCity_client() {
		return city_client;
	}

	public void setCity_client(String city_client) {
		this.city_client = city_client;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getUnit_value() {
		return unit_value;
	}

	public void setUnit_value(String unit_value) {
		this.unit_value = unit_value;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("demo_bill [id=");
		builder.append(id);
		builder.append(", seller=");
		builder.append(seller);
		builder.append("]");
		return builder.toString();
	}
}
