package net.dragberry.expman.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.dragberry.expman.dao.CustomerDao;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.query.CustomerQuery;
import net.dragberry.expman.result.ResultList;

@Stateless
public class CustomerServiceBean implements CustomerService {

	@Inject
	private CustomerDao customerDao;
	
	@Override
	public ResultList<Customer> fetchCustomerList(CustomerQuery query) {
		return customerDao.findCustomers(query);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerDao.createCustomer(customer);
	}

	@Override
	public Customer findCustomerById(Long customerKey) {
		return customerDao.findCustomerByKey(customerKey);
	}

}
