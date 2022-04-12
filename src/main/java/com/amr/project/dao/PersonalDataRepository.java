package com.amr.project.dao;

import com.amr.project.model.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
}
