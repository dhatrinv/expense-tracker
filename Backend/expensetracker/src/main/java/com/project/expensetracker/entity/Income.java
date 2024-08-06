package com.project.expensetracker.entity;

import com.project.expensetracker.dto.IncomeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;
    public IncomeDTO getIncomeDTO() {
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(id);
        incomeDTO.setTitle(title);
        incomeDTO.setDescription(description);
        incomeDTO.setCategory(category);
        incomeDTO.setDate(date);
        incomeDTO.setAmount(amount);
        return incomeDTO;
    }
}
