package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    //TODO: проверить "правильность" параметра unique (могут быть товары с одинаковыми наименованиями у разных Shops, Users, CartItems)
    @Column(name = "name"/*, unique = true*/)
    private String name;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "count")
    private int count;

    @Column(name = "rating")
    private double rating;


    private String description;
    private int discount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    private CartItem cartItem;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private List<Image> images;


    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews;


    @ManyToMany(mappedBy = "items")
    private List<Favorite> favorites;


    //TODO: произведена замена типа связи с order (с ManyToMany на ManyToOne)
    //Комментарий: для каждой корзины (заказа) нужна отдельная запись в таблице "item"
    //так как информация о кол-ве заказанного товара хранится в Сущности Item
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;


    /*@ManyToMany(mappedBy = "itemsInOrder")
    @OrderBy("orderDate ASC")
    private List<Order> orders = new ArrayList<>();*/

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;


    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;


    public boolean isPretendedToBeDeleted() {
        return isPretendedToBeDeleted;
    }



    public void setPretendedToBeDeleted(boolean pretendedToBeDeleted) {
        isPretendedToBeDeleted = pretendedToBeDeleted;
    }
}
