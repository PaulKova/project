package com.amr.project.service.abstracts;

import com.amr.project.model.dto.DiscountDto;

import java.util.List;

public interface DiscountService {
    List<DiscountDto> getAllDiscounts();

    DiscountDto getDiscountById(Long id);

    void saveDiscount(DiscountDto discountDto);

    void updateDiscount(DiscountDto discountDto);

    void deleteDiscount(Long id);
}
