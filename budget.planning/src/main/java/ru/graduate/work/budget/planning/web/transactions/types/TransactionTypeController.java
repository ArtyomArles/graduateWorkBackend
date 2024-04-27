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
@RequestMapping(value = "/transaction-types")
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;

    @GetMapping("/search")
    public ResponseEntity<List<TransactionType>> transactionTypesSearch(@RequestParam(name = "title", required = false) String title) {
        final List<TransactionType> transactionTypes = transactionTypeService.listTransactionTypes((title));
        return transactionTypes != null && !transactionTypes.isEmpty()
                ? new ResponseEntity<>(transactionTypes, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionType> transactionTypeInfo(@PathVariable Long id) {
        TransactionType transactionType = transactionTypeService.getTransactionTypeById(id);
        return transactionType != null
                ? new ResponseEntity<>(transactionType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransactionType(@RequestBody TransactionType transactionType) {
        transactionTypeService.saveTransactionType(transactionType);
        return new ResponseEntity<>(transactionType, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransactionType(@PathVariable Long id) {
        transactionTypeService.deleteTransactionType(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editTransactionType(@RequestBody TransactionType transactionType) {
        transactionTypeService.editTransactionType(transactionType);
        return new ResponseEntity<>(transactionType, HttpStatus.OK);
    }
}
