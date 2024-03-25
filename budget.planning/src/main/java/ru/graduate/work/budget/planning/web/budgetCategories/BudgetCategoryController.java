package ru.graduate.work.budget.planning.web.budgetCategories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BudgetCategoryController {
    private final BudgetCategoryService categoryService;

    @GetMapping("/categories")
    public String categories(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("categories", categoryService.listCategories(title));
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String categoryInfo(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "category";
    }

    @PostMapping("/categories/create")
    public String createCategory(BudgetCategory category) {
//        category.setId(100L);
        categoryService.saveBudgetCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteBudgetCategory(id);
        return "redirect:/categories";
    }

    @PostMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable Long id, BudgetCategory category) {
        categoryService.editBudgetCategory(id, category);
        return "redirect:/categories/{id}";
    }
}
