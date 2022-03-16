package com.amr.project.model.dto;

import com.amr.project.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private Long id;

    private String phone;
    private String firstName;
    private String lastName;
    private int age;


    private GenderDto gender;
    private Calendar birthday;
    private UserDto user;
}
