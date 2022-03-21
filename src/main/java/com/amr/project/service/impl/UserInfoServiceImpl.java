package com.amr.project.service.impl;

import com.amr.project.converter.mappers.UserInfoMapper;
import com.amr.project.dao.UserInfoRepository;
import com.amr.project.model.dto.UserInfoDto;
import com.amr.project.model.entity.UserInfo;
import com.amr.project.service.abstracts.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfoDto> getAllUserInfo() {
        return userInfoMapper.toDtoList(userInfoRepository.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public UserInfoDto getUserInfoById(Long id) {
        return userInfoMapper.toDto(userInfoRepository.getById(id), new CycleAvoidingMappingContext());
    }

    @Override
    public void saveUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.toEntity(userInfoDto, new CycleAvoidingMappingContext());
        userInfoRepository.saveAndFlush(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.toEntity(userInfoDto, new CycleAvoidingMappingContext());
        userInfoRepository.saveAndFlush(userInfo);
    }

    @Override
    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteById(id);
    }
}

