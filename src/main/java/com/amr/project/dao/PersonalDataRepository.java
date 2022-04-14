package com.amr.project.dao;

import com.amr.project.model.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

    List<PersonalData> getByStatus(String personal_data_status);
}
