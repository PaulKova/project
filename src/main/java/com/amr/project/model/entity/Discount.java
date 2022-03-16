package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "discount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {
    //TODO скидка для постоянного покупателя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
