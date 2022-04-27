package com.amr.project.converter.mappers;


import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.GenderDto;
import com.amr.project.model.dto.UserInfoDto;
import com.amr.project.model.entity.UserInfo;
import com.amr.project.model.enums.Gender;
import org.mapstruct.Builder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = UserMapper.class)
public interface UserInfoMapper extends MapperInterface<UserInfoDto, UserInfo> {
}
