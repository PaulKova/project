package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CityDto {

    private Long id;
    private String name;

    @JsonBackReference
    private List<AddressDto> addresses;
    @JsonManagedReference
    private CountryDto country;

}