package net.dragberry.expman.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CE")
public class CarExpense extends Expense {

	private static final long serialVersionUID = 9155699991669039905L;

}
