package com.amr.project.service.abstracts;

import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.enums.PersonalDataStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalDataService {

    void deletePersonalData(Long id);

    void changeStatus(Long id, PersonalDataStatus personalDataStatus, String comment);
}
