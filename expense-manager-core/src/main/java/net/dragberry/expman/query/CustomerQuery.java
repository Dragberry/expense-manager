package net.dragberry.expman.query;

import java.util.Set;

import net.dragberry.expman.domain.Role;

public class CustomerQuery extends SortableQuery {

	private static final long serialVersionUID = 7849789100403222003L;
	
	private String customerName;
	
	private Boolean enabled;
	
	private Set<Role> roles;
	
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
