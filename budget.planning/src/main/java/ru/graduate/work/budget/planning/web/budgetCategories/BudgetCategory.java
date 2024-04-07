package ru.graduate.work.budget.planning.web.budgetCategories;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="budgetcategories")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetcategories_id_generator")
    @SequenceGenerator(name = "budgetcategories_id_generator", sequenceName = "budgetcategories_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

}
