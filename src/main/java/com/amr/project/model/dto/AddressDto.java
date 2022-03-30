package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class AddressDto {
    private Long id;
    private String cityIndex;
    private String street;
    private String house;

    //@JsonManagedReference
    private CityDto city;
    //@JsonBackReference
    private List<UserDto> users;
    //@JsonBackReference
    private List<ShopDto> shops;
    //@JsonBackReference
    private List<OrderDto> orders;
}
