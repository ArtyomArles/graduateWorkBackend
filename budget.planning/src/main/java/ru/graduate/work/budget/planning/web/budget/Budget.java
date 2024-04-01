package ru.graduate.work.budget.planning.web.budget;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="budgets")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "budgets_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "year")
    private Integer year;
    @Column(name = "transactions")
    private List<Long> transactions;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "balance")
    private BigDecimal balance;
}
