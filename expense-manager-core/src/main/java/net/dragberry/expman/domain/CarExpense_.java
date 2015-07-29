package net.dragberry.expman.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CarExpense.class)
public class CarExpense_ {
	
	public static volatile SingularAttribute<CarExpense, Long> expenseKey;
	public static volatile SingularAttribute<CarExpense, CarExpense.Type> type;
}
