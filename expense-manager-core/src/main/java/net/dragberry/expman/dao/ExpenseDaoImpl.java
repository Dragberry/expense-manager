package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Expense;

public class ExpenseDaoImpl extends AbstractDao implements ExpenseDao {

	@Override
	public Expense createExpense(Expense expense) {
		getEntityManager().persist(expense);
		return expense;
	}

}
