package ru.graduate.work.budget.planning.web.transactions.types;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;

    @GetMapping("/transaction-types")
    public String transactionTypes() {
        return "transaction-types";
    }
    @GetMapping("/transaction-types/search")
    public ResponseEntity<List<TransactionType>> transactionTypesSearch(@RequestParam(name = "title", required = false) String title) {
        final List<TransactionType> transactionTypes = transactionTypeService.listTransactionTypes((title));
        return transactionTypes != null && !transactionTypes.isEmpty()
                ? new ResponseEntity<>(transactionTypes, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/transaction-types/{id}")
    public String transactionType(@PathVariable Long id) {
        return "transaction-type";
    }
    @GetMapping("/transaction-types/search/{id}")
    public ResponseEntity<TransactionType> transactionTypeInfo(@PathVariable Long id) {
        TransactionType transactionType = transactionTypeService.getTransactionTypeById(id);
        return transactionType != null
                ? new ResponseEntity<>(transactionType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/transaction-types/create")
    public ResponseEntity<?> createTransactionType(@RequestBody TransactionType transactionType) {
        transactionTypeService.saveTransactionType(transactionType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/transaction-types/delete/{id}")
    public ResponseEntity<?> deleteTransactionType(@PathVariable Long id) {
        transactionTypeService.deleteTransactionType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transaction-types/edit/{id}")
    public ResponseEntity<?> editTransactionType(@PathVariable Long id, @RequestBody TransactionType transactionType) {
        transactionTypeService.editTransactionType(id, transactionType);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
