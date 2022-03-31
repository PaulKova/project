package com.amr.project.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {

    private static final long serialVersionUID = 156977875169457L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column
    private String description;

    private int count;      //что за поле? Нет описания

    private double rating;

    // Сущность Country входит в Address
    /*@ManyToOne(fetch = FetchType.LAZY)
    private Country country;*/


    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Item> items;


    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Review> reviews;


    @OneToOne(mappedBy = "shop", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH},
            fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Image logo;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;


    @OneToOne(mappedBy = "shop", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH},
            fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private CartItem cartItem;


    @OneToMany(
            mappedBy = "shop",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Feedback> feedbacks;


    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true)
    @ToString.Exclude
    private List<Discount> discounts;


    @ManyToMany(mappedBy = "shops")
    @ToString.Exclude
    private List<Favorite> favorites;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Address address;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "shop_id")
    @ToString.Exclude
    private List<Coupon> coupons;


    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    public boolean isPretendedToBeDeleted() {
        return isPretendedToBeDeleted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shop shop = (Shop) o;
        return id != null && Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
