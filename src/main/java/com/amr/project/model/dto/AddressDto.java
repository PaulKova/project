package com.amr.project.model.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

//ToDo Убрать все Getter and Setter

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String cityIndex;
    private String street;
    private String house;


    private CityDto city;
    private List<UserDto> users;
    private List<ShopDto> shops;
    private List<OrderDto> orders;
}
