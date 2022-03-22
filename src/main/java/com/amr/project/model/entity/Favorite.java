package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToMany
    @JoinTable(name = "favorite_shop",
            joinColumns = @JoinColumn(name = "favorite_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Shop> shops;


    @ManyToMany
    @JoinTable(name = "favorite_item",
            joinColumns = @JoinColumn(name = "favorite_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}