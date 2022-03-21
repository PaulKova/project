package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "min_order")
    private Integer minOrder;

    @Column(name = "percentage")
    private Integer percentage;

    @Column(name = "fixed_discount")
    private Integer fixedDiscount;


    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;


}
