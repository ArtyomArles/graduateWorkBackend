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

    @GetMapping("/search")
    public ResponseEntity<List<Budget>> budgetsSearch(@RequestParam(name = "year", required = false) Integer year) {
        List<Budget> budgets = budgetService.listBudgets(year);
        return budgets != null && !budgets.isEmpty()
                ? new ResponseEntity<>(budgets, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{year}")
    public ResponseEntity<Budget> budgetByYear(@PathVariable Integer year) {
        Budget budget = budgetService.getBudgetByYear(year).get(0);
        return budget != null
                ? new ResponseEntity<>(budget, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBudget(@RequestBody Budget budget) {
        budget.setBalance(budget.getSum());
        budgetService.saveBudget(budget);
        return new ResponseEntity<>(budget, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editCategory(@RequestBody Budget budget) {
        budgetService.editBudget(budget);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
}
