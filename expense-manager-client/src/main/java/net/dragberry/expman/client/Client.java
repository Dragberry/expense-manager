package net.dragberry.expman.client;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.dragberry.expman.business.BusinessException;
import net.dragberry.expman.business.CounterPartyService;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.business.InterchangeService;
import net.dragberry.expman.domain.CarExpense;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Currency;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Expense;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.domain.CarExpense.Type;
import net.dragberry.expman.query.CounterPartyListQuery;
import net.dragberry.expman.query.InterchangeListQuery;
import net.dragberry.expman.query.InterchangeTypeListQuery;
import net.dragberry.expman.query.sort.SortOrder;
import net.dragberry.expman.result.ResultList;

public class Client {

	public static void main(String[] args) throws Exception {
		invokeStatelessBean();

	}

	@SuppressWarnings({"unchecked", "unused", "rawtypes"})
	private static void invokeStatelessBean() throws NamingException, BusinessException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "expense-manager-ear";
		final String moduleName = "expense-manager-business";
		final String distinctName = "";
		final String viewClassName = CustomerService.class.getName();

		CustomerService cs = (CustomerService) context.lookup("ejb:expense-manager-ear/expense-manager-business//CustomerServiceBean!net.dragberry.expman.business.CustomerService");
		InterchangeService is =  (InterchangeService) context.lookup("ejb:expense-manager-ear/expense-manager-business//InterchangeServiceBean!net.dragberry.expman.business.InterchangeService");
		CounterPartyService cps = (CounterPartyService) context.lookup("ejb:expense-manager-ear/expense-manager-business//CounterPartyServiceBean!net.dragberry.expman.business.CounterPartyService");
		
		Customer customer = cs.findCustomerById(1L);
		
		InterchangeTypeListQuery interchangeTypeListQuery = new InterchangeTypeListQuery();
		interchangeTypeListQuery.setCustomer(customer);
		ResultList<InterchangeType> interchangeTypeList = is.fetchInterchangeTypeList(interchangeTypeListQuery);
		
		CarExpense expense = new CarExpense();
		expense.setCost(new BigDecimal("11100"));
		expense.setQuantity(45);
		expense.setCustomer(customer);
		expense.setName("АИ-92");
		Interchange intch = new Interchange();
		intch.setProcessingDate(new Date());
		intch.setCustomer(customer);
		intch.setInterchangeType(interchangeTypeList.getList().get(0));
		expense.setInterchange(intch);
		expense.setType(Type.REGULAR);
		Expense expense1 = is.createExpense(expense);
		
		
		
		Map<InterchangeType, Map<Currency, BigDecimal>> expenseMap = is.calculateExpenses(customer.getCustomerKey(), interchangeTypeList.getList());
		
		
		Map<Currency, BigDecimal> balances = is.getAllRealTimeBalances(customer.getCustomerKey());
		
		BigDecimal balance = is.getRealTimeBalance(customer.getCustomerKey(), "BYR");
		
		CounterParty cp = new CounterParty();
		cp.setCustomer(customer);
		cp.setName("Мама");
		cp = cps.createCounterParty(cp);
		
		CounterPartyListQuery counterPartyListQuery = new CounterPartyListQuery();
		counterPartyListQuery.setName("Мама");
		ResultList<CounterParty> cpl = cps.fetchCounterPartyList(counterPartyListQuery);
		
		InterchangeType type = new InterchangeType();
		type.setName("DebtPayment");
		type.setType(TransactionType.CREDIT.getType());
		type.setCustomer(customer);
		
		type = is.createInterchangeType(type);
		
		Interchange i = new Interchange();
		i.setAmount(new BigDecimal("300000"));
		i.setCurrency("BYR");
		i.setCustomer(customer);
		i.setDescription("Заправка на АЗС на логойском тракте");
		i.setInterchangeType(type);
		i.setCounterParty(cp);
		i.setProcessingDate(new Date());
		
		i = is.createInterchange(i);
		
		InterchangeListQuery interchangeListQuery = new InterchangeListQuery();
		interchangeListQuery.setFromAmount(new BigDecimal("100"));
		interchangeListQuery.setToAmount(new BigDecimal("1000000"));
		interchangeListQuery.setToDate(new Date());
		interchangeListQuery.setCurrencyList(Arrays.asList(new String[] { "BYR" }));
		interchangeListQuery.setCounterParty(Arrays.asList(new CounterParty[] { cp }));
		interchangeListQuery.addSortItem("processingDate", SortOrder.ASCENDING, Interchange.class, 0);
		
		ResultList<Interchange> interchangeList = is.fetchInterchangeList(interchangeListQuery);
		
		System.out.println();
	}
}
