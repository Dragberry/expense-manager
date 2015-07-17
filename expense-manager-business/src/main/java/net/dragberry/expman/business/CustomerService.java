package net.dragberry.expman.business;

import javax.ejb.Remote;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Role;
import net.dragberry.expman.query.CustomerQuery;
import net.dragberry.expman.result.ResultList;

@Remote
public interface CustomerService {
	
	public Customer findCustomerById(Long customerKey);

	public ResultList<Customer> fetchCustomerList(CustomerQuery query);
	
	public Customer createCustomer(Customer customer);
	
	public Role findRoleByName(String roleName);

}
