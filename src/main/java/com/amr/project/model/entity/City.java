package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(
            mappedBy = "city",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH},
            orphanRemoval = true
    )
    private List<Address> addresses;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public City(Long id) {
        this.id = id;
    }
}