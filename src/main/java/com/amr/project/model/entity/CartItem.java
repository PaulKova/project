package com.amr.project.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cart_item")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class CartItem implements Serializable {

    private static final long serialVersionUID = 669774687654689846L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "quantity")
    private int quantity; //"Лишнее" поле (информация о кол-ве товара хранится в поле "count" сущности "Item"


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @ToString.Exclude
    private Shop shop;


    @OneToMany(
            mappedBy = "cartItem",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Item> itemsInCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartItem cartItem = (CartItem) o;
        return id != null && Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
