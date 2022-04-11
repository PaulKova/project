package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class PersonalData {

    @Id
    @Column(name = "passport",nullable = false, unique = true)
    private int passport;

    @Column(name = "dateOfIssue", nullable = false, unique = true)
    private int dateOfIssue;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "placeOfBirth", nullable = false)
    private String placeOfBirth;


    @OneToMany(
            mappedBy = "personalData",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH},
            orphanRemoval = true)
    @ToString.Exclude
    private List<Image> listOfImages;

}
