package ru.graduate.work.budget.planning.web.transactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.graduate.work.budget.planning.web.budget.Budget;
import ru.graduate.work.budget.planning.web.budget.BudgetRepository;
import ru.graduate.work.budget.planning.web.budgetCategories.BudgetCategory;
import ru.graduate.work.budget.planning.web.budgetCategories.BudgetCategoryRepository;
import ru.graduate.work.budget.planning.web.transactions.types.TransactionType;
import ru.graduate.work.budget.planning.web.transactions.types.TransactionTypeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final BudgetRepository budgetRepository;
    public List<Transaction> listTransactions(String title) {
        if (title != "" && title != null)
            return transactionRepository.findByTitle(title);
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void saveTransaction(Transaction transaction) {
        TransactionType transactionType = transactionTypeRepository.findById(transaction.getTransactionTypeId()).orElse(null);
        BudgetCategory budgetCategory = budgetCategoryRepository.findById(transaction.getCategoryId()).orElse(null);

        transaction.setTransactionType(transactionType);
        transaction.setCategory(budgetCategory);
        transaction.setYear(transaction.getTransactionDate().getYear() + 1900);
        transactionRepository.save(transaction);
        updateBudgets();
    }

    private void updateBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        for (Budget budget: budgets) {
            List<Transaction> transactions = this.getTransactionsByYear(budget.getYear());
            budget.setTransactions(transactions);
            BigDecimal balance = budget.getSum();

            for (Transaction _transaction: transactions) {
                balance = balance.add(this.getTransactionById((_transaction.getId())).getSum());
            }

            budget.setBalance(balance);
            budgetRepository.save(budget);
        }
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        updateBudgets();
    }

    public void editTransaction(Long id, Transaction editedTransaction) {
        this.saveTransaction(editedTransaction);
    }

    public List<Transaction> getTransactionsByYear(int year) {
        return transactionRepository.findByYear(year);
    }
}
