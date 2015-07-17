package net.dragberry.expman.client;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Customer_;
import net.dragberry.expman.domain.Role;
import net.dragberry.expman.query.CustomerQuery;
import net.dragberry.expman.query.sort.SortOrder;
import net.dragberry.expman.result.ResultList;

public class Client {

	public static void main(String[] args) throws Exception {
		invokeStatelessBean();

	}

	@SuppressWarnings({"unchecked", "unused", "rawtypes"})
	private static void invokeStatelessBean() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "expense-manager-ear";
		final String moduleName = "expense-manager-business";
		final String distinctName = "";
		final String viewClassName = CustomerService.class.getName();

		CustomerService cs = (CustomerService) context.lookup("ejb:expense-manager-ear/expense-manager-business//CustomerServiceBean!net.dragberry.expman.business.CustomerService");

		Customer customer = new Customer();
		customer.setCustomerName("added");
		customer.setEnabled(true);
		
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRoleName("added");
		role.setRoleKey(1L);
		roles.add(role);
		customer = cs.createCustomer(customer);
		
		CustomerQuery query = new CustomerQuery();
		query.setCustomerName("ad");
		query.setEnabled(true);
		query.setPageNumber(2);
		query.setPageSize(2);
		query.addSortItem("enabled", SortOrder.DESCENDING, Customer.class, 1);
		
		ResultList<Customer> list = cs.fetchCustomerList(query);
		
		Customer cust = cs.findCustomerById(3l);
		System.out.println();
	}
}
