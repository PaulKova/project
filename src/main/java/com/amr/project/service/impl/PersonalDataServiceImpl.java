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
    public List<PersonalDataDto> getAllWaitingPersonalData() {
        return personalDataMapper
                .toDtoList(personalDataRepository.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public PersonalDataDto getPersonalDataById(int id) {
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
//        здесь нужно отправить уведомление клиенту что поменялся статуc
//
            personalData.setStatus(PersonalDataStatus.WAITING);
        personalDataRepository.saveAndFlush(personalData);
    }

    @Override
    public void deletePersonalData(int id) {
        personalDataRepository.deleteById(id);
    }

    @Override
    public void changeStatusToRejected(int personalDataId, String reason) {
        PersonalData personalData = personalDataRepository.getById(personalDataId);
        personalData.setStatus(PersonalDataStatus.REJECTED);
        updatePersonalData(personalData);
        //
        //        здесь нужно отправить уведомление клиенту что поменялся статуc
        //
    }

    @Override
    public void changeStatusToConfirmed(int personalDataId) {
        PersonalData personalData = personalDataRepository.getById(personalDataId);
        personalData.setStatus(PersonalDataStatus.CONFIRMED);
        updatePersonalData(personalData);
        //
        //        здесь нужно отправить уведомление клиенту что поменялся статуc
        //
    }
}
