package com.amr.project.model.dto;

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
public class CouponDto {

    private Long id;
    private Calendar start;
    private Calendar end;

    //@JsonManagedReference
    private UserDto user;
}
