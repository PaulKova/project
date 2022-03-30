package com.amr.project.model.dto;

import com.amr.project.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Calendar;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class UserInfoDto {
    private Long id;

    private String phone;
    private String firstName;
    private String lastName;
    private int age;
    private Calendar birthday;

    //@JsonManagedReference
    private GenderDto gender;
    //@JsonManagedReference
    private UserDto user;
}
