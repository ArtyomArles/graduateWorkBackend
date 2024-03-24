package ru.graduate.work.budget.planning.web.budgetCategories;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BudgetCategory {
    private Long id;
    private String title;
    private String description;

}
