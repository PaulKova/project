package com.amr.project.converter;


import com.amr.project.model.dto.UserInfoDTO;
import com.amr.project.model.entity.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserInfoMapper {
    UserInfoDTO toDTO(UserInfo userInfo);
    UserInfo toEntity(UserInfoDTO userInfoDTO);
}
