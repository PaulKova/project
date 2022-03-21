package com.amr.project.service.impl;

import com.amr.project.converter.mappers.DiscountMapper;
import com.amr.project.dao.DiscountRepository;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.service.abstracts.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mapstruct.example.mapper.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public List<DiscountDto> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return discountMapper.toDtoList(discounts, new CycleAvoidingMappingContext());
    }

    @Override
    public DiscountDto getDiscountById(Long id) {
        Discount discount = discountRepository.getById(id);
        return discountMapper.toDto(discount, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveDiscount(DiscountDto discountDto) {
        Discount discount = discountMapper.toEntity(discountDto, new CycleAvoidingMappingContext());
        discountRepository.saveAndFlush(discount);
    }

    @Override
    public void updateDiscount(DiscountDto discountDto) {
        Discount discount = discountMapper.toEntity(discountDto, new CycleAvoidingMappingContext());
        discountRepository.saveAndFlush(discount);
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
