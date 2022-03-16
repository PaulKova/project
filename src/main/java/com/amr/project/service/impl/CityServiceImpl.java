package com.amr.project.service.impl;

import com.amr.project.converter.mappers.CityMapper;
import com.amr.project.dao.CityRepository;
import com.amr.project.model.dto.CityDto;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.City;
import com.amr.project.service.abstracts.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.toDtoList(cities);
    }

    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.getById(id);
        return cityMapper.toDto(city);
    }

    @Override
    public void saveCity(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        cityRepository.saveAndFlush(city);
    }

    @Override
    public void updateCity(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        cityRepository.saveAndFlush(city);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
