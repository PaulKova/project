package com.amr.project.service.abstracts;

import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.PersonalData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalDataService {

    List<PersonalDataDto> getByStatus(String personal_data_status);

    void updatePersonalData(PersonalDataDto personalDataDto);


}
