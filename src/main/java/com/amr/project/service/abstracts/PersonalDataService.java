package com.amr.project.service.abstracts;

import com.amr.project.model.dto.PersonalDataDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalDataService {

    List<PersonalDataDto> getAllPersonalData();

    PersonalDataDto getPersonalDataById(Long id);

    void savePersonalData(PersonalDataDto personalDataDto);

    void updatePersonalData(PersonalDataDto personalDataDto);

    void deletePersonalData(Long id);

    void changeStatusToRejected(Long personalDataId);

    void changeStatusToConfirmed(Long personalDataId);
}
