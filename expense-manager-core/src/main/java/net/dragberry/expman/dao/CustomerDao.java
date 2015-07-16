package net.dragberry.expman.dao;

import java.util.List;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.query.CustomerQuery;

public interface CustomerDao {
	
	Customer findCustomerByKey(Long customerKey);
	
	List<Customer> findCustomers(CustomerQuery query);
	
	Customer createCustomer(Customer customer);
	
	Customer deleteCustomer(Customer customer);

}
