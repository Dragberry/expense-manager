package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Expense;

public interface ExpenseDao {

	Expense createExpense(Expense expense);
	
}
