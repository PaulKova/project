package com.amr.project.dao;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
