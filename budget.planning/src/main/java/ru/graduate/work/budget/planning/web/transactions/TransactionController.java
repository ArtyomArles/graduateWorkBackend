package ru.graduate.work.budget.planning.web.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/transactions")
    public String transactions() {
        return "transactions";
    }
    @GetMapping("/transactions/search")
    public ResponseEntity<List<Transaction>> transactionsSearch(@RequestParam(name = "title", required = false) String title) {
        final List<Transaction> transactions = transactionService.listTransactions(title);
        return transactions != null && !transactions.isEmpty()
                ? new ResponseEntity<>(transactions, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public String transaction(@PathVariable Long id) {
        return "transaction";
    }

    @GetMapping("/transactions/search/{id}")
    public ResponseEntity<Transaction> transactionInfo(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return transaction != null
                ? new ResponseEntity<>(transaction, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/transactions/create")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/transactions/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transactions/edit/{id}")
    public ResponseEntity<?> editTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transactionService.editTransaction(id, transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
