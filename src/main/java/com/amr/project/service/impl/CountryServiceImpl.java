package com.amr.project.service.impl;

import com.amr.project.converter.mappers.CountryMapper;
import com.amr.project.dao.CountryRepository;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.service.abstracts.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getAlCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    @Override
    public CountryDto getCountryById(Long id) {
        Country country = countryRepository.getById(id);
        return countryMapper.toDto(country);
    }

    @Override
    public void saveCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);
        countryRepository.saveAndFlush(country);
    }

    @Override
    public void updateCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);
        countryRepository.saveAndFlush(country);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
