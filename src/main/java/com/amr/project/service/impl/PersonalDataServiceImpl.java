package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.dao.PersonalDataRepository;
import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.enums.PersonalDataStatus;
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
    public PersonalDataDto getPersonalDataById(Long id) {
        return personalDataMapper.toDto(personalDataRepository.getById(id), new CycleAvoidingMappingContext());
    }

    @Override
    public void savePersonalData(PersonalDataDto personalDataDto) {
        PersonalData personalData = personalDataMapper.toEntity(personalDataDto, new CycleAvoidingMappingContext());
        personalDataRepository.saveAndFlush(personalData);
    }
}
