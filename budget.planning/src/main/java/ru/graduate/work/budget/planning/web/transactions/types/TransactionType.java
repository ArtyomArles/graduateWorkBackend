package ru.graduate.work.budget.planning.web.transactions.types;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="transactiontypes")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactiontypes_id_generator")
    @SequenceGenerator(name = "transactiontypes_id_generator", sequenceName = "transactiontypes_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
