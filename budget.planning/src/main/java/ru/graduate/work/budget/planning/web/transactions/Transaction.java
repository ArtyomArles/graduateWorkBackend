package ru.graduate.work.budget.planning.web.transactions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="transactions")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_id_generator")
    @SequenceGenerator(name = "transactions_id_generator", sequenceName = "transactions_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "transaction_type")
    private Long transactionType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "year")
    private int year;
}
