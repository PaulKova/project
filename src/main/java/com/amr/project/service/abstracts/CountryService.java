package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.CouponDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> getAllCountries();

    CountryDto getCountryById(Long id);

    void saveCountry(CountryDto countryDto);

    void updateCountry(CountryDto countryDto);

    void deleteCountry(Long id);
}
