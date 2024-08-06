package com.project.expensetracker.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class IncomeDTO {
    private long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;
}
