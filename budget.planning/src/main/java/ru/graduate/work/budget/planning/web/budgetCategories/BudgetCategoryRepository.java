package ru.graduate.work.budget.planning.web.budgetCategories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
    List<BudgetCategory> findByTitle(String title);
}
