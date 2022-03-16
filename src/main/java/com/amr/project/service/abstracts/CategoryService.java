package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.ItemDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    void saveCategory(CategoryDto itemDto);

    void updateCategory(CategoryDto itemDto);

    void deleteCategory(Long id);
}
