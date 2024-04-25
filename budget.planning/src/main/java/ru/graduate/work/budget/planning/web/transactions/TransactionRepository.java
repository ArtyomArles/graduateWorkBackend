package ru.graduate.work.budget.planning.web.transactions;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTitle(String title, Sort sort);
    List<Transaction> findByYear(int year);
}
