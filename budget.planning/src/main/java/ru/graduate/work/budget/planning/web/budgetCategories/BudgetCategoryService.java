package ru.graduate.work.budget.planning.web.budgetCategories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BudgetCategoryService {
    private final BudgetCategoryRepository categoryRepository;

    public List<BudgetCategory> listCategories(String title) {
        if (title != "" && title != null)
            return categoryRepository.findByTitle(title, Sort.by("id").descending());
        return categoryRepository.findAll(Sort.by("id").descending());
    }

    public BudgetCategory getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void saveBudgetCategory(BudgetCategory budgetCategory) {
        categoryRepository.save(budgetCategory);
    }

    public void deleteBudgetCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void editBudgetCategory(BudgetCategory editedBudgetCategory) {
        this.saveBudgetCategory(editedBudgetCategory);
    }

}
