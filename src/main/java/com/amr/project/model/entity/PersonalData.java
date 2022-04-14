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
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "passport",nullable = false, unique = true)
    @NonNull
    private int passport;

    @Column(name = "dateOfIssue", nullable = false)
    @NonNull
    private Date dateOfIssue;


    @Column(name = "authority", nullable = false)
    @NonNull
    private String authority;

    @Column(name = "placeOfBirth", nullable = false)
    @NonNull
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
    @JoinTable(name="PersonalData_image", joinColumns=@JoinColumn(name="PersonalDataId", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="ImageId", referencedColumnName = "id" ))
    @ToString.Exclude
    private List<Image> listOfImages;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "personalDataStatus")
    @NonNull
    private PersonalDataStatus status;


}
