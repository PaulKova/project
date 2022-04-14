package com.amr.project.dao;

import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.enums.PersonalDataStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

    List<PersonalData> getByStatus(PersonalDataStatus personal_data_status);
}
