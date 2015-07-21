package net.dragberry.expman.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTERCHANGE")
public class Interchange implements Serializable {

	private static final long serialVersionUID = -5641038821205965857L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERCHANGE_KEY")
	private Long interchangeKey;
	
	@Column(name = "AMOUNT")
	private BigInteger amount;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "INTERCHANGE_TYPE")
	private InterchangeType interchangeType;

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getInterchangeKey() {
		return interchangeKey;
	}

	public void setInterchangeKey(Long interchangeKey) {
		this.interchangeKey = interchangeKey;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public InterchangeType getInterchangeType() {
		return interchangeType;
	}

	public void setInterchangeType(InterchangeType interchangeType) {
		this.interchangeType = interchangeType;
	}

}
