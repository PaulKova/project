package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String cityIndex;
    private String street;
    private String house;

    @JsonManagedReference
    private CityDto city;
    @JsonBackReference
    private List<UserDto> users;
    @JsonBackReference
    private List<ShopDto> shops;
    @JsonBackReference
    private List<OrderDto> orders;
}
