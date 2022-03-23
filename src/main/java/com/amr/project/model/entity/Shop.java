package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import org.springframework.data.relational.core.mapping.Table;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
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

    private int count;

    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country location;


    @OneToMany(
            mappedBy = "shop",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Item> items;


    @OneToMany(
            mappedBy = "shop",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews;


    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Image logo;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private CartItem cartItem;


    @OneToMany(
            mappedBy = "shop",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Feedback> feedbacks;


    @OneToMany(
            mappedBy = "shop",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true)
    private List<Discount> discounts;


    @ManyToMany(mappedBy = "shops")
    private List<Favorite> favorites;


    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "shop_id")
    private List<Coupon> coupons;


    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    public boolean isPretendedToBeDeleted() {
        return isPretendedToBeDeleted;
    }



}
