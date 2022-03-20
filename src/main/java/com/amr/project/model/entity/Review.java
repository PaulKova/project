package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "dignity")
    private String dignity; //плюсы

    @Column(name = "flaw")
    private String flaw; //минусы

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date date;

    @Column(name = "rating")
    private int rating;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;


    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;


    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

}
