package com.project.expensetracker.dto;

import com.project.expensetracker.entity.Expense;
import com.project.expensetracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {

    private List<Expense> expensesList;

    private List<Income> incomeList;
}
