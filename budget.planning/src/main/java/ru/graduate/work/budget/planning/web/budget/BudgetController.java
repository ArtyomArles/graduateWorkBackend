package ru.graduate.work.budget.planning.web.budget;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.graduate.work.budget.planning.web.transactions.Transaction;
import ru.graduate.work.budget.planning.web.transactions.TransactionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;
    private final TransactionService transactionService;

    @GetMapping("/budgets")
    public String budgets(@RequestParam(name = "year", required = false) Integer year, Model model) {
        model.addAttribute("budgets", budgetService.listBudgets(year));
        return "budgets";
    }

    @GetMapping("/budgets/{id}")
    public String budgetInfo(@PathVariable Long id, Model model) {
        Budget budget = budgetService.getBudgetById(id);
        List<Transaction> transactions = new ArrayList<>();
        budget.getTransactions().forEach(transaction -> transactions.add(transactionService.getTransactionById(transaction)));
        model.addAttribute("transactions", transactions);
        model.addAttribute("budget", budget);
        return "budget";
    }

    @PostMapping("/budgets/create")
    public String createBudget(Budget budget) {
        budget.setBalance(budget.getSum());
        budgetService.saveBudget(budget);
        return "redirect:/budgets";
    }

    @PostMapping("/budgets/delete/{id}")
    public String deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return "redirect:/budgets";
    }

    @PostMapping("/budgets/edit/{id}")
    public String editCategory(@PathVariable Long id, Budget budget) {
        budgetService.editBudget(id, budget);
        return "redirect:/budgets/{id}";
    }
}
