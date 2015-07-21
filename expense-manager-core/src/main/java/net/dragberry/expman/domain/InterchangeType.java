package net.dragberry.expman.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTERCHANGE_TYPE")
public class InterchangeType implements Serializable {

	private static final long serialVersionUID = -5046144195093972064L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERCHANGE_TYPE_KEY")
	private Long interchangeTypeKey;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "TYPE")
	private String type;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;

	public Long getInterchangeTypeKey() {
		return interchangeTypeKey;
	}

	public void setInterchangeTypeKey(Long interchangeTypeKey) {
		this.interchangeTypeKey = interchangeTypeKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
