package net.dragberry.expman.business;

import java.util.List;

import javax.ejb.Remote;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.query.CustomerQuery;

@Remote
public interface CustomerService {
	
	public Customer findCustomerById(Long customerKey);

	public List<Customer> fetchCustomerList(CustomerQuery query);
	
	public Customer createCustomer(Customer customer);

}
