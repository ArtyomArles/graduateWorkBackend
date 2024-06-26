package ru.graduate.work.budget.planning.web.transactions.types;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionTypeService {
    private final TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> listTransactionTypes(String title) {
        if (title != "" && title != null)
            return transactionTypeRepository.findByTitle(title, Sort.by("id").descending());
        return transactionTypeRepository.findAll(Sort.by("id").descending());
    }

    public TransactionType getTransactionTypeById(Long id) {
        return transactionTypeRepository.findById(id).orElse(null);
    }

    public void saveTransactionType(TransactionType transactionType) {
        transactionTypeRepository.save(transactionType);
    }

    public void deleteTransactionType(Long id) {
        transactionTypeRepository.deleteById(id);
    }

    public void editTransactionType(TransactionType editedTransactionType) {
        this.saveTransactionType(editedTransactionType);
    }
}
