package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CountryDto {

    private Long id;
    private String name;

    @JsonBackReference
    private List<ShopDto> shops;
    @JsonBackReference
    private List<CityDto> cities;
}
