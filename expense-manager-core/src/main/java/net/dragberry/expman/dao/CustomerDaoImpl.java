package net.dragberry.expman.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Customer_;
import net.dragberry.expman.query.CustomerQuery;

@Stateless
public class CustomerDaoImpl extends AbstractDao implements CustomerDao {

	@Override
	public Customer findCustomerByKey(Long customerKey) {
		return getEntityManager().find(Customer.class, customerKey);
	}

	@Override
	public List<Customer> findCustomers(CustomerQuery customerQuery) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customerRoot = cq.from(Customer.class);
		if (customerQuery != null) {
			Predicate where = null;
			where = addAndLikeExpression(customerQuery.getCustomerName(), Customer_.customerName, where, cb, customerRoot);
			where = addAndEqualsExpression(customerQuery.getEnabled(), Customer_.enabled, where, cb, customerRoot);
			addWhereClause(cq, where);
		}
		return getEntityManager().createQuery(cq).getResultList();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		getEntityManager().persist(customer);
		return customer;
	}
	
	@Override
	public Customer deleteCustomer(Customer customer) {
		getEntityManager().remove(customer);
		return customer;
	}

}
