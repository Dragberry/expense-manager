package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.query.CustomerQuery;
import net.dragberry.expman.result.ResultList;

public interface CustomerDao {
	
	Customer findCustomerByKey(Long customerKey);
	
	ResultList<Customer> findCustomers(CustomerQuery query);
	
	Customer createCustomer(Customer customer);
	
	Customer deleteCustomer(Customer customer);

}
