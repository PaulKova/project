package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CityDto {

    private Long id;
    private String name;

    private List<AddressDto> addresses;
    private CountryDto country;

}