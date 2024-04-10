package ru.graduate.work.budget.planning.web.budget;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.graduate.work.budget.planning.web.transactions.Transaction;
import ru.graduate.work.budget.planning.web.transactions.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final TransactionService transactionService;

    public List<Budget> listBudgets(Integer year) {
        if (year != null)
            return budgetRepository.findByYear(year);
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    public void saveBudget(Budget budget) {
        List<Transaction> transactions = transactionService.getTransactionsByYear(budget.getYear());
        budget.setTransactions(transactions);
        BigDecimal balance = budget.getSum();
        for (Transaction transaction: transactions) {
            balance = balance.add(transactionService.getTransactionById((transaction.getId())).getSum());
        }
        budget.setBalance(balance);
        budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }

    public void editBudget(Long id, Budget editedBudget) {
        this.saveBudget(editedBudget);
    }

}
