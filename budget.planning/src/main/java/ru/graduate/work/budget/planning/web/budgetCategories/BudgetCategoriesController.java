package ru.graduate.work.budget.planning.web.budgetCategories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BudgetCategoriesController {
    private final BudgetCategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.listCategories());
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String categoryInfo(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "category";
    }

    @PostMapping("/categories/create")
    public String createCategory(BudgetCategory category) {
        categoryService.saveBudgetCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteBudgetCategory(id);
        return "redirect:/categories";
    }
}
