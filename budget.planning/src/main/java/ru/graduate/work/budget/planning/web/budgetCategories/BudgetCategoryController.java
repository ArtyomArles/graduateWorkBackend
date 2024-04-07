package ru.graduate.work.budget.planning.web.budgetCategories;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class BudgetCategoryController {
    private final BudgetCategoryService categoryService;

    @GetMapping("")
    public String categories() {
        return "categories";
    }

    @GetMapping("/search")
    public ResponseEntity<List<BudgetCategory>> categoriesSearch(@RequestParam(name = "title", required = false) String title) {
        List<BudgetCategory> categories = categoryService.listCategories(title);
        return categories != null && !categories.isEmpty()
                ? new ResponseEntity<>(categories, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String category(@PathVariable Long id) {
        return "category";
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<BudgetCategory> categoryInfo(@PathVariable Long id) {
        BudgetCategory budgetCategory = categoryService.getCategoryById(id);
        return budgetCategory != null
                ? new ResponseEntity<>(budgetCategory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody BudgetCategory category) {
        categoryService.saveBudgetCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteBudgetCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Long id, @RequestBody BudgetCategory category) {
        categoryService.editBudgetCategory(id, category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
