package ru.graduate.work.budget.planning.web.transactions.types;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;

    @GetMapping("/transaction-types")
    public String transactionTypes(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("transactionTypes", transactionTypeService.listTransactionTypes(title));
        return "transaction-types";
    }

    @GetMapping("/transaction-types/{id}")
    public String transactionTypeInfo(@PathVariable Long id, Model model) {
        TransactionType transactionType = transactionTypeService.getTransactionTypeById(id);
        model.addAttribute("transactionType", transactionType);
        return "transaction-type";
    }

    @PostMapping("/transaction-types/create")
    public String createTransactionType(TransactionType transactionType) {
        transactionTypeService.saveTransactionType(transactionType);
        return "redirect:/transaction-types";
    }

    @PostMapping("/transaction-types/delete/{id}")
    public String deleteTransactionType(@PathVariable Long id) {
        transactionTypeService.deleteTransactionType(id);
        return "redirect:/transaction-types";
    }

    @PostMapping("/transaction-types/edit/{id}")
    public String editTransactionType(@PathVariable Long id, TransactionType transactionType) {
        transactionTypeService.editTransactionType(id, transactionType);
        return "redirect:/transaction-types/{id}";
    }
}
