package net.dragberry.expman.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Expense.class)
public class Expense_ {
	
	public static volatile SingularAttribute<Expense, Long> expenseKey;
	public static volatile SingularAttribute<Expense, String> name;
	public static volatile SingularAttribute<Expense, BigDecimal> cost;
	public static volatile SingularAttribute<Expense, Interchange> interchange;
	public static volatile SingularAttribute<Expense, Customer> customer;

}
