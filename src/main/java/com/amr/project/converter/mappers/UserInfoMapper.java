package com.amr.project.converter.mappers;


import com.amr.project.model.dto.GenderDto;
import com.amr.project.model.dto.UserInfoDto;
import com.amr.project.model.entity.UserInfo;
import com.amr.project.model.enums.Gender;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserInfoMapper {

    UserInfoDto toDto(UserInfo userInfo);

    UserInfo toEntity(UserInfoDto userInfoDto);

    List<UserInfoDto> toDtoList(List<UserInfo> userInfos);

    List<UserInfo> toEntityList(List<UserInfoDto> userInfoDtoS);


    /*@InheritInverseConfiguration
    Gender genderDtoToGender(GenderDto genderDto)*/;


}
