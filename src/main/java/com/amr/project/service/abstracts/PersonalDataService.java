package com.amr.project.service.abstracts;

import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.enums.PersonalDataStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalDataService {

    List<PersonalDataDto> getByStatus(PersonalDataStatus personal_data_status);

    void updatePersonalData(PersonalDataDto personalDataDto);

    PersonalDataDto getPersonalDataById(Long id);

    void savePersonalData(PersonalDataDto personalDataDto);

    void deletePersonalData(Long id);

    void changeStatus(Long id, PersonalDataStatus personalDataStatus, String comment);
}

