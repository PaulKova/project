package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "coupon")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    //TODO разовый скидочный купон для покупателя, добавить поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "start")
    private Calendar start;

    @Column(name = "end")
    private Calendar end;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
