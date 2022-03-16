package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    //TODO товары в корзине, продумать какие поля им нужны, нужны-ли связи?

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;


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
    private List<Item> itemList;
}
