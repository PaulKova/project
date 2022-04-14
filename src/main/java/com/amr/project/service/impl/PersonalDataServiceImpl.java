package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.dao.PersonalDataRepository;
import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {

    private final PersonalDataRepository personalDataRepository;
    private final PersonalDataMapper personalDataMapper;


    @Override
    public List<PersonalDataDto> getByStatus(String personal_data_status) {
        List<PersonalData> personalDataList = personalDataRepository.getByStatus(personal_data_status);
        return personalDataMapper.toDtoList(personalDataList, new CycleAvoidingMappingContext());
    }


    @Override
    public void updatePersonalData(PersonalDataDto personalDataDto) {
        PersonalData personalData1 = personalDataMapper.toEntity(personalDataDto, new CycleAvoidingMappingContext());
        personalDataRepository.saveAndFlush(personalData1);
    }
}