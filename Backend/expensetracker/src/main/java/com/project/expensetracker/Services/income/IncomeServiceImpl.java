package com.project.expensetracker.Services.income;

import com.project.expensetracker.dto.IncomeDTO;
import com.project.expensetracker.entity.Income;
import com.project.expensetracker.repsitory.IncomeRepositiry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepositiry incomeRepositiry;

    public Income postIncome(IncomeDTO incomeDTO) {
        return savOrUpdateIncome(new Income(), incomeDTO);
    }

    private Income savOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setDescription(incomeDTO.getDescription());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDate(incomeDTO.getDate());
        return incomeRepositiry.save(income);
    }

    public Income updateIncome(Long id,IncomeDTO incomeDTO) {
        Optional<Income> optionalIncome = incomeRepositiry.findById(id);
        if (optionalIncome.isPresent()) {
            return savOrUpdateIncome(optionalIncome.get(), incomeDTO);
        }
        else{
            throw new EntityNotFoundException("Income not found for id " + id);
        }
    }

    public List<IncomeDTO> getAllIncomes() {
        return incomeRepositiry.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDTO)
                .collect(Collectors.toList());
    }

    public IncomeDTO getIncomeById(Long id) {
        Optional<Income> optionalIncome = incomeRepositiry.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDTO();
        }
        else{
            throw new EntityNotFoundException("Income not found for id " + id);
        }
    }



    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = incomeRepositiry.findById(id);
        if (optionalIncome.isPresent()) {
            incomeRepositiry.delete(optionalIncome.get());
        }
        else {
            throw new EntityNotFoundException("Income not found for id " + id);
        }
    }

}
