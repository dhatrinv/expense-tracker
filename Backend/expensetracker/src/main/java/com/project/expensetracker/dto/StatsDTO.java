package com.project.expensetracker.dto;

import com.project.expensetracker.entity.Expense;
import com.project.expensetracker.entity.Income;
import lombok.Data;

@Data
public class StatsDTO {

    private Double income;
    private Double expense;
    private Income latestIncome;
    public Expense latestExpense;
    private Double balance;
    private Double minIncome;
    private Double maxIncome;
    private Double minExpense;
    private Double maxExpense;


}
