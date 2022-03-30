package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class CountryDto {

    //@Column(name = "countryDtoId")
    private Long id;
    private String name;

    /*//@JsonBackReference
    private List<ShopDto> shops;*/
    //@JsonBackReference("country-city")
    private List<CityDto> cities;
}
