package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.converter.mappers.UserMapper;
import com.amr.project.dao.PersonalDataRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.PersonalDataStatus;
import com.amr.project.service.abstracts.PersonalDataService;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailUserAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {

    private final PersonalDataRepository personalDataRepository;
    private final PersonalDataMapper personalDataMapper;
    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final EmailUserAssistant emailUserAssistant;


    @Override
    public void deletePersonalData(Long id) {
        User user = userRepository.getUserByPersonalData(personalDataRepository.getById(id));
        user.setPersonalData(null);
        user.setIdentification(false);
        userRepository.saveAndFlush(user);
        personalDataRepository.deleteById(id);
    }

    @Override
    public void changeStatus(Long id, PersonalDataStatus personalDataStatus, String comment) {
        PersonalData personalData = personalDataRepository.getById(id);
        personalData.setStatus(personalDataStatus);
        personalData.setComment(comment);
        personalDataRepository.saveAndFlush(personalData);

        User user = userRepository.getUserByPersonalData(personalData);
        if(personalData.getStatus() == PersonalDataStatus.CONFIRMED) {
            user.setIdentification(true);
            userRepository.saveAndFlush(user);
        }
        mailSender.send(emailUserAssistant.trackedEmailIdentificationUpdate(user));
    }
}
