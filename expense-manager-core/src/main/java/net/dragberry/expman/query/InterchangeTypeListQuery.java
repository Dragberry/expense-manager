package net.dragberry.expman.query;

import net.dragberry.expman.domain.Customer;

public class InterchangeTypeListQuery extends SortableQuery {

	private static final long serialVersionUID = 9138627599123221030L;
	
	private String name;
	
	private String type;
	
	private Customer customer;

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
