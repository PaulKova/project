package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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


    private boolean isModerated = false;
    private boolean isModerateAccept = false;
    private String moderatedRejectReason;

}
