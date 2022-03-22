package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.Calendar;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private Long id;
    private Calendar start;
    private Calendar end;

    @JsonManagedReference
    private UserDto user;
}
