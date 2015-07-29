package net.dragberry.expman.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@Entity
@DiscriminatorValue(value = "CE")
@SecondaryTable(name = "CAR_EXPENSE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "EXPENSE_KEY"))
public class CarExpense extends Expense {

	private static final long serialVersionUID = 9155699991669039905L;
	
	public static enum Type {
		REGULAR, UNEXPECTED, FUEL
	}
	
	@Enumerated
	@Column(name = "TYPE", table = "CAR_EXPENSE")
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
