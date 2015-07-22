package net.dragberry.expman.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.InterchangeType;

public class InterchangeListQuery extends SortableQuery {

	private static final long serialVersionUID = -317245878093348075L;
	
	private Customer customer;
	
	private List<InterchangeType> interchangeTypeList;
	
	private List<CounterParty> counterParty;
	
	private BigDecimal fromAmount;
	
	private BigDecimal toAmount;
	
	private List<String> currencyList;
	
	private Date fromDate;
	
	private Date toDate;
	
	private String description;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<InterchangeType> getInterchangeTypeList() {
		return interchangeTypeList;
	}

	public void setInterchangeTypeList(List<InterchangeType> interchangeTypeList) {
		this.interchangeTypeList = interchangeTypeList;
	}

	public List<CounterParty> getCounterPartyList() {
		return counterParty;
	}

	public void setCounterParty(List<CounterParty> counterParty) {
		this.counterParty = counterParty;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public List<String> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<String> currencyList) {
		this.currencyList = currencyList;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
