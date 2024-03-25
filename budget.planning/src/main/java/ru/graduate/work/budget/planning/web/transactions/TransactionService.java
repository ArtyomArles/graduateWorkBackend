package ru.graduate.work.budget.planning.web.transactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> listTransactions(String title) {
        if (title != "" && title != null)
            return transactionRepository.findByTitle(title);
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void saveTransaction(Transaction transaction) {
        log.info("Saving new {}", transaction);
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public void editTransaction(Long id, Transaction editedTransaction) {
        Transaction transaction = this.getTransactionById(id);
        transaction.setId(editedTransaction.getId());
        transaction.setTitle(editedTransaction.getTitle());
        transaction.setCategoryId(editedTransaction.getCategoryId());
        transaction.setDescription(editedTransaction.getDescription());
        transaction.setSum(editedTransaction.getSum());
        transaction.setTransactionType(editedTransaction.getTransactionType());
        transaction.setTransactionDate(editedTransaction.getTransactionDate());
        this.saveTransaction(transaction);
    }
}
