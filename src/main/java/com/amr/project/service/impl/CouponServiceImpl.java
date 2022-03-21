package com.amr.project.service.impl;

import com.amr.project.converter.mappers.CouponMapper;
import com.amr.project.dao.CouponRepository;
import com.amr.project.model.dto.CouponDto;
import com.amr.project.model.entity.Coupon;
import com.amr.project.service.abstracts.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mapstruct.example.mapper.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    @Override
    public List<CouponDto> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return couponMapper.toDtoList(coupons, new CycleAvoidingMappingContext());
    }

    @Override
    public CouponDto getCouponById(Long id) {
        Coupon coupon = couponRepository.getById(id);
        return couponMapper.toDto(coupon, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveCoupon(CouponDto couponDto) {
        Coupon coupon = couponMapper.toEntity(couponDto, new CycleAvoidingMappingContext());
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void updateCoupon(CouponDto couponDto) {
        Coupon coupon = couponMapper.toEntity(couponDto, new CycleAvoidingMappingContext());
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }
}
