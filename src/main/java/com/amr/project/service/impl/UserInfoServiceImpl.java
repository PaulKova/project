package com.amr.project.service.impl;

import com.amr.project.converter.mappers.UserInfoMapper;
import com.amr.project.dao.UserInfoRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.dto.UserInfoDto;
import com.amr.project.model.entity.Message;
import com.amr.project.model.entity.User;
import com.amr.project.model.entity.UserInfo;
import com.amr.project.service.abstracts.UserInfoService;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfoDto> getAllUserInfo() {
        return userInfoMapper.toDtoList(userInfoRepository.findAll());
    }

    @Override
    public UserInfoDto getUserInfoById(Long id) {
        return userInfoMapper.toDto(userInfoRepository.getById(id));
    }

    @Override
    public void saveUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo =  userInfoMapper.toEntity(userInfoDto);
        userInfoRepository.saveAndFlush(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo =  userInfoMapper.toEntity(userInfoDto);
        userInfoRepository.saveAndFlush(userInfo);
    }

    @Override
    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteById(id);
    }
}

