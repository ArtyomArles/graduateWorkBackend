package ru.graduate.work.budget.planning.web.budget;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.graduate.work.budget.planning.web.transactions.Transaction;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="budgets")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgets_id_generator")
    @SequenceGenerator(name = "budgets_id_generator", sequenceName = "budgets_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "year")
    private Integer year;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "budgets_transactions",
            joinColumns = {@JoinColumn(name = "budget_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "transaction_id", referencedColumnName = "id")})
    private List<Transaction> transactions;
}
