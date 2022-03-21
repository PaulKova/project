package com.amr.project.model.dto;

import lombok.*;

import java.util.Calendar;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private Long id;
    private Calendar start;
    private Calendar end;

    private UserDto user;
}
