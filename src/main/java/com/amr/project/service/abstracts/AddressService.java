package com.amr.project.service.abstracts;

import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.CategoryDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAllAddresses();

    AddressDto getAddressById(Long id);

    void saveAddress(AddressDto addressDto);

    void updateAddress(AddressDto addressDto);

    void deleteAddress(Long id);
}
