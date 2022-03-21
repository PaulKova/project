package com.amr.project.model.dto;

import lombok.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class CountryDto {

    private Long id;
    private String name;


    private List<ShopDto> shops;
    private List<CityDto> cities;
}
