package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="addrezz")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "city_index", nullable = false, unique = true)
    private String cityIndex;

    @Column(name = "street", nullable = false, unique = true)
    private String street;

    @Column(name = "house", nullable = false, unique = true)
    private String house;


    @ManyToOne(fetch = FetchType.LAZY)
    private City city;


    @OneToMany(
            mappedBy = "address",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true
    )
    private List<User> users;


    @OneToMany(
            mappedBy = "address",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true
    )
    private List<Shop> shops;


    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "address")
    private List<Order> orders;
}
