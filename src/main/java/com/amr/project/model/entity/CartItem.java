package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "quantity")
    private int quantity; //"Лишнее" поле (информация о кол-ве товара хранится в поле "count" сущности "Item"


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;


    @OneToMany(
            mappedBy = "cartItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Item> itemsInCart;
}
