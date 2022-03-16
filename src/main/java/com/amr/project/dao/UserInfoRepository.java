package com.amr.project.dao;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
