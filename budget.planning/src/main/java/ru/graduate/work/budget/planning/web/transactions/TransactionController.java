package ru.graduate.work.budget.planning.web.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.graduate.work.budget.planning.web.budgetCategories.BudgetCategoryService;
import ru.graduate.work.budget.planning.web.transactions.types.TransactionTypeService;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final BudgetCategoryService categoryService;
    private final TransactionTypeService transactionTypeService;

    @GetMapping("/transactions")
    public String transactions(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("transactions", transactionService.listTransactions(title));
        model.addAttribute("transactionTypes", transactionTypeService.listTransactionTypes(""));
        model.addAttribute("categories", categoryService.listCategories(""));
        return "transactions";
    }

    @GetMapping("/transactions/{id}")
    public String transactionInfo(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("transactionTypes", transactionTypeService.listTransactionTypes(""));
        model.addAttribute("currentTransactionType", transactionTypeService.getTransactionTypeById(transaction.getTransactionType()));
        model.addAttribute("categories", categoryService.listCategories(""));
        model.addAttribute("currentCategory", categoryService.getCategoryById(transaction.getCategoryId()));
        return "transaction";
    }

    @PostMapping("/transactions/create")
    public String createTransaction(Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    @PostMapping("/transactions/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }

    @PostMapping("/transactions/edit/{id}")
    public String editTransaction(@PathVariable Long id, Transaction transaction) {
        transactionService.editTransaction(id, transaction);
        return "redirect:/transactions/{id}";
    }
}
