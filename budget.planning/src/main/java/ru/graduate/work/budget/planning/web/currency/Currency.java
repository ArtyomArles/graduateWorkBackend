package ru.graduate.work.budget.planning.web.currency;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "currencies")
public class Currency {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currencies_id_generator")
    @SequenceGenerator(name = "currencies_id_generator", sequenceName = "currencies_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "currency_rate")
    private BigDecimal currencyRate;
    @Column(name = "country")
    private String country;
    @Column(name = "icon")
    private String icon;
    @Column(name = "description")
    private String description;
    @Column(name = "code")
    private String code;
}
