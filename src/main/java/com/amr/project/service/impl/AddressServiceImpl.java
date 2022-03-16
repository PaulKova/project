package com.amr.project.service.impl;

import com.amr.project.converter.mappers.AddressMapper;
import com.amr.project.dao.AddressRepository;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import com.amr.project.service.abstracts.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addressMapper.toDtoList(addresses);
    }

    @Override
    public AddressDto getAddressById(Long id) {
        Address address = addressRepository.getById(id);
        return addressMapper.toDto(address);
    }

    @Override
    public void saveAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        addressRepository.saveAndFlush(address);
    }

    @Override
    public void updateAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        addressRepository.saveAndFlush(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
