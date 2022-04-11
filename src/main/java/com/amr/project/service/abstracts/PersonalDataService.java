package com.amr.project.service.abstracts;

import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.entity.PersonalData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalDataService {

    List<PersonalDataDto> getAllPersonalData();

    List<PersonalDataDto> getAllWaitingPersonalData();

    PersonalDataDto getPersonalDataById(int id);

    void savePersonalData(PersonalDataDto personalDataDto);

    void updatePersonalData(PersonalDataDto personalDataDto);

    void deletePersonalData(int id);

    void changeStatusToRejected(int personalDataId, String reason);

    void changeStatusToConfirmed(int personalDataId);
}
