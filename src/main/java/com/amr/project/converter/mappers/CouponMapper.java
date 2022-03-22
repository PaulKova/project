package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.CouponDto;
import com.amr.project.model.entity.Coupon;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = UserMapper.class)
public interface CouponMapper extends MapperInterface<CouponDto, Coupon> {

}
