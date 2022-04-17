package com.amr.project.model.entity;

import com.amr.project.model.enums.PersonalDataStatus;
import com.amr.project.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "passport",nullable = false, unique = true)
    private Long passport;

    @Column(name = "dateOfIssue", nullable = false)
    private Date dateOfIssue;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "placeOfBirth", nullable = false)
    private String placeOfBirth;

    @Column(name = "comment")
    private String comment;


    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH},
            orphanRemoval = true)

    @ToString.Exclude
    private List<Image> listOfImages;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "personalDataStatus")
    private PersonalDataStatus status;

}
