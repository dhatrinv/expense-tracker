package com.project.expensetracker.Services.stats;

import com.project.expensetracker.dto.GraphDTO;
import com.project.expensetracker.dto.StatsDTO;
import com.project.expensetracker.entity.Expense;
import com.project.expensetracker.entity.Income;
import com.project.expensetracker.repsitory.ExpenseRepository;
import com.project.expensetracker.repsitory.IncomeRepositiry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final IncomeRepositiry incomeRepositiry;
    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);
        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpensesList(expenseRepository.findByDateBetween(startDate, endDate));
        graphDTO.setIncomeList(incomeRepositiry.findByDateBetween(startDate,endDate));
        return graphDTO;

    }

    public StatsDTO getStats(){
        Double totalIncome=incomeRepositiry.sumAllAmounts();
        Double totalExpense=expenseRepository.sumAllAmounts();
        Optional<Income> optionalIncome=incomeRepositiry.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense=expenseRepository.findFirstByOrderByDateDesc();
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome-totalExpense);

        List<Income> incomeList=incomeRepositiry.findAll();
        List<Expense> expenseList=expenseRepository.findAll();

        OptionalDouble minIncome =incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome =incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense =expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense =expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMaxExpense(minExpense.isPresent() ? maxExpense.getAsDouble():null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble():null);
        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble():null);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble():null);


        return statsDTO;
    }
}
