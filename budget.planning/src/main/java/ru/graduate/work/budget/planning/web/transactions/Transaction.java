package ru.graduate.work.budget.planning.web.transactions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.graduate.work.budget.planning.users.role.Role;
import ru.graduate.work.budget.planning.web.budgetCategories.BudgetCategory;
import ru.graduate.work.budget.planning.web.transactions.types.TransactionType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

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

    @Column(name = "transaction_type_id")
    private Long transactionTypeId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "year")
    private int year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions_with_types",
            joinColumns = {@JoinColumn(name = "transaction_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "transactiontype_id", referencedColumnName = "id")})
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions_with_categories",
            joinColumns = {@JoinColumn(name = "transaction_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private BudgetCategory category;
}
