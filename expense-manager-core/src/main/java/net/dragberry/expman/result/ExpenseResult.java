package net.dragberry.expman.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import net.dragberry.expman.domain.Currency;

public class ExpenseResult implements Serializable {

	private static final long serialVersionUID = -3660391700370125358L;
	
	private Map<Currency, BigDecimal> expenses = null;

	public Map<Currency, BigDecimal> getExpenses() {
		return expenses;
	}

	public void setExpenses(Map<Currency, BigDecimal> expenses) {
		this.expenses = expenses;
	}
	
}
