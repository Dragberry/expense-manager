package net.dragberry.expman.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="EXPENSE_TYPE", discriminatorType=DiscriminatorType.STRING, length=2)
public abstract class Expense implements Serializable {
	
	private static final long serialVersionUID = 3719556698834955736L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXPENSE_KEY")
	private Long expenseKey;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	@Column(name = "COST")
	private BigDecimal cost;
	
	@OneToOne
	@JoinColumn(name = "INTERCHANGE_KEY")
	private Interchange interchange;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_KEY", referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;

	public Long getExpenseKey() {
		return expenseKey;
	}

	public void setExpenseKey(Long expenseKey) {
		this.expenseKey = expenseKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Interchange getInterchange() {
		return interchange;
	}

	public void setInterchange(Interchange interchange) {
		this.interchange = interchange;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
