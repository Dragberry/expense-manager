package net.dragberry.expman.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.business.InterchangeService;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
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
		InterchangeService is =  (InterchangeService) context.lookup("ejb:expense-manager-ear/expense-manager-business//InterchangeServiceBean!net.dragberry.expman.business.InterchangeService");
		
		Customer customer = cs.findCustomerById(1L);
		
		InterchangeType type = new InterchangeType();
		type.setName("Fuel");
		type.setType("C");
		type.setCustomer(customer);
		
		type = is.createInterchangeType(type);
		
		Interchange i = new Interchange();
		i.setAmount(new BigDecimal("500000"));
		i.setCurrency("BYR");
		i.setCustomer(customer);
		i.setDescription("Заправка на АЗС на логойском тракте");
		i.setInterchangeType(type);
		i.setProcessingDate(new Date());
		
		i = is.createInterchange(i);
		
		System.out.println();
	}
}
