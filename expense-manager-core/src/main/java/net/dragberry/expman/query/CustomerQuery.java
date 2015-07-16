package net.dragberry.expman.query;

import java.io.Serializable;

public class CustomerQuery implements Serializable {

	private static final long serialVersionUID = 7849789100403222003L;
	
	private String customerName;
	
	private Boolean enabled;
	
	public CustomerQuery() {
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
