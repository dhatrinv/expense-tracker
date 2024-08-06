package com.project.expensetracker.Services.stats;

import com.project.expensetracker.dto.GraphDTO;
import com.project.expensetracker.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();

    StatsDTO getStats();
}
