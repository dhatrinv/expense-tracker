package com.project.expensetracker.Services.expense;

import com.project.expensetracker.dto.ExpenseDTO;
import com.project.expensetracker.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense postExpense(ExpenseDTO expenseDTO);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense updateExpense (Long id, ExpenseDTO expenseDTO);
    void deleteExpense(Long id);




}
