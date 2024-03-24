package ru.graduate.work.budget.planning.web.budgetCategories;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetCategoryService {
    private List<BudgetCategory> categories = new ArrayList<>();
    private long id = 0;

    {
        categories.add(new BudgetCategory(++id, "Цифровая техника", "Статья учета цифровой техники"));
        categories.add(new BudgetCategory(++id, "Канцелярия", "Статья учета канцелярии"));
        categories.add(new BudgetCategory(++id, "Автомобили", "Статья учета автомобилей"));
        categories.add(new BudgetCategory(++id, "Мебель", "Статья учета мебели"));
        categories.add(new BudgetCategory(++id, "Мероприятия", "Статья учета мероприятий"));
    }

    public List<BudgetCategory> listCategories() {return categories;}

    public void saveBudgetCategory(BudgetCategory budgetCategory) {
        budgetCategory.setId(++id);
        categories.add(budgetCategory);
    }

    public void deleteBudgetCategory(Long id) {
        categories.removeIf(category -> category.getId().equals(id));
    }

    public BudgetCategory getCategoryById(Long id) {
        for (BudgetCategory category: categories) {
            if (category.getId().equals(id)) return category;
        }
        return null;
    }
}
