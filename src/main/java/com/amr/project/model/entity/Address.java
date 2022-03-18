package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table()
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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
