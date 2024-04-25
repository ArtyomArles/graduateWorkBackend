package ru.graduate.work.budget.planning.web.transactions.types;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    List<TransactionType> findByTitle(String title, Sort sort);
}
