package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@Builder
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
