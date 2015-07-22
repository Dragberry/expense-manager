package net.dragberry.expman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Customer_;
import net.dragberry.expman.domain.Role;
import net.dragberry.expman.domain.Role_;
import net.dragberry.expman.query.CustomerQuery;
import net.dragberry.expman.result.ResultList;

public class CustomerDaoImpl extends AbstractDao implements CustomerDao {

	@Override
	public Customer findCustomerByKey(Long customerKey) {
		return getEntityManager().find(Customer.class, customerKey);
	}

	@Override
	public ResultList<Customer> findCustomers(CustomerQuery customerQuery) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Customer> customerRoot = cq.from(Customer.class);
		Root<Customer> countRoot = cqCount.from(Customer.class);
		
		Join<Customer, Role> joinCustomerRole = customerRoot.join(Customer_.roles, JoinType.INNER);
        Join<Customer, Role> joinCustomerRoleCount = countRoot.join(Customer_.roles, JoinType.INNER);
        
		
		if (customerQuery != null) {
			Predicate where = getWhereClause(customerRoot, cb, customerQuery, joinCustomerRole);
			Predicate whereCount = getWhereClause(countRoot, cb, customerQuery, joinCustomerRoleCount);
			addWhereClause(cq, where);
			addWhereClause(cqCount, whereCount);
		}
		
		cqCount.select(cb.count(countRoot));
		
		Map<Class<?>, From<?, ?>>  sortMap = new HashMap<Class<?>, From<?,?>>();
		sortMap.put(Customer.class, customerRoot);
		cq.orderBy(getOrders(customerQuery.getSortList(), sortMap, cb));
		
		TypedQuery<Customer> query = getEntityManager().createQuery(cq);
        setPageableParams(customerQuery, query);
		
		
		List<Customer> list = query.getResultList();
        Long count = getEntityManager().createQuery(cqCount).getSingleResult();
        
		ResultList<Customer> resultList = new ResultList<Customer>();
		resultList.setList(list);
		resultList.setCount(count);
        resultList.setPageNumber(customerQuery.getPageNumber());
        resultList.setPageSize(customerQuery.getPageSize());
		return resultList;
	}
	
	private Predicate getWhereClause(Root<Customer> root, CriteriaBuilder cb, CustomerQuery customerQuery, Join<Customer, Role> joinCustomerRole) {
		Predicate where = null;
		where = addAndLikeExpression(customerQuery.getCustomerName(), Customer_.customerName, where, cb, root);
		where = addAndEqualsExpression(customerQuery.getEnabled(), Customer_.enabled, where, cb, root);
		In<Long> roles = cb.in(joinCustomerRole.get(Role_.roleKey));
		for (Role role : customerQuery.getRoles()) {
			roles.value(role.getRoleKey());
		}
		where = andExpression(where, roles, cb);
		return where;
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
