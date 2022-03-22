package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    //TODO разовый скидочный купон для покупателя, добавить поля (например размер скидки)
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
