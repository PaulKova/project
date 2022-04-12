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
    public List<PersonalDataDto> getAllPersonalData() {
        return personalDataMapper.toDtoList(personalDataRepository.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public PersonalDataDto getPersonalDataById(Long id) {
        return personalDataMapper.toDto(personalDataRepository.getById(id), new CycleAvoidingMappingContext());
    }

    @Override
    public void savePersonalData(PersonalDataDto personalDataDto) {
        PersonalData personalData = personalDataMapper.toEntity(personalDataDto, new CycleAvoidingMappingContext());
//
//        здесь нужно отправить уведомление админу или модератору что нужно проверить данные
//
        personalDataRepository.saveAndFlush(personalData);
    }

    @Override
    public void updatePersonalData(PersonalDataDto personalDataDto) {
        PersonalData personalData = personalDataMapper.toEntity(personalDataDto, new CycleAvoidingMappingContext());
//
//        здесь нужно отправить уведомление клиенту что поменялся статус
//
            personalData.setStatus(PersonalDataStatus.WAITING);
        personalDataRepository.saveAndFlush(personalData);
    }

    @Override
    public void deletePersonalData(Long id) {
        personalDataRepository.deleteById(id);
    }

    @Override
    public void changeStatusToRejected(Long personalDataId) {
        PersonalDataDto personalDataDto = personalDataMapper.toDto(personalDataRepository.getById(personalDataId), new CycleAvoidingMappingContext());
        personalDataDto.setStatus(PersonalDataStatus.REJECTED);
        updatePersonalData(personalDataDto);
        //
        //        здесь нужно отправить уведомление клиенту что поменялся статус
        //
    }

    @Override
    public void changeStatusToConfirmed(Long personalDataId) {
        PersonalDataDto personalDataDto = personalDataMapper.toDto (personalDataRepository.getById(personalDataId), new CycleAvoidingMappingContext());
        personalDataDto.setStatus(PersonalDataStatus.CONFIRMED);
        updatePersonalData(personalDataDto);
        //
        //        здесь нужно отправить уведомление клиенту что поменялся статус
        //
    }
}
