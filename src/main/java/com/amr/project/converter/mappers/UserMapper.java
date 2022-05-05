package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {OrderMapper.class, FavoriteMapper.class,
        ImageMapper.class, CountryMapper.class, ShopMapper.class,
        DiscountMapper.class, MessageMapper.class, ChatMapper.class, FeedbackMapper.class,
        ReviewMapper.class, UserInfoMapper.class, FavoriteMapper.class, AddressMapper.class})
public interface UserMapper extends MapperInterface<UserDto, User> {


}

