package ru.graduate.work.budget.planning.web.transactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.graduate.work.budget.planning.web.budget.Budget;
import ru.graduate.work.budget.planning.web.budget.BudgetRepository;
import ru.graduate.work.budget.planning.web.budgetCategories.BudgetCategory;
import ru.graduate.work.budget.planning.web.budgetCategories.BudgetCategoryRepository;
import ru.graduate.work.budget.planning.web.currency.Currency;
import ru.graduate.work.budget.planning.web.currency.CurrencyRepository;
import ru.graduate.work.budget.planning.web.transactions.types.TransactionType;
import ru.graduate.work.budget.planning.web.transactions.types.TransactionTypeRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final CurrencyRepository currencyRepository;
    private final BudgetRepository budgetRepository;
    public List<Transaction> listTransactions(String title) {
        if (title != "" && title != null)
            return transactionRepository.findByTitle(title, Sort.by("id").descending());
        return transactionRepository.findAll(Sort.by("id").descending());
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void saveTransaction(Transaction transaction) {
        TransactionType transactionType = transactionTypeRepository.findById(transaction.getTransactionTypeId()).orElse(null);
        BudgetCategory budgetCategory = budgetCategoryRepository.findById(transaction.getCategoryId()).orElse(null);
        Currency currency = currencyRepository.findById(transaction.getCurrencyId()).orElse(null);

        transaction.setTransactionType(transactionType);
        transaction.setCategory(budgetCategory);
        transaction.setCurrency(currency);
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
                Currency currency = currencyRepository.findById(_transaction.getCurrencyId()).orElse(null);
                balance = balance.add(this.getTransactionById((_transaction.getId())).getSum().multiply(currency.getCurrencyRate()));
            }

            budget.setBalance(balance);
            budgetRepository.save(budget);
        }
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        updateBudgets();
    }

    public void editTransaction(Transaction editedTransaction) {
        this.saveTransaction(editedTransaction);
    }

    public List<Transaction> getTransactionsByYear(int year) {
        return transactionRepository.findByYear(year);
    }
}
