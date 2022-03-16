package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> getAllCities();

    CityDto getCityById(Long id);

    void saveCity(CityDto cityDto);

    void updateCity(CityDto cityDto);

    void deleteCity(Long id);
}
