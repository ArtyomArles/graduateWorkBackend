package ru.graduate.work.budget.planning.users.role;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_generator")
    @SequenceGenerator(name = "roles_id_generator", sequenceName = "roles_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
