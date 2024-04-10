package ru.graduate.work.budget.planning.web.budget;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping("")
    public String budgets() {
        return "budgets";
    }

    @GetMapping("/search")
    public ResponseEntity<List<Budget>> budgetsSearch(@RequestParam(name = "year", required = false) Integer year) {
        List<Budget> budgets = budgetService.listBudgets(year);
        return budgets != null && !budgets.isEmpty()
                ? new ResponseEntity<>(budgets, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String budget(@PathVariable Long id) {
        return "budget";
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Budget> budgetInfo(@PathVariable Long id) {
        Budget budget = budgetService.getBudgetById(id);
        return budget != null
                ? new ResponseEntity<>(budget, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBudget(@RequestBody Budget budget) {
        budget.setBalance(budget.getSum());
        budgetService.saveBudget(budget);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Long id, @RequestBody Budget budget) {
        budgetService.editBudget(id, budget);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
