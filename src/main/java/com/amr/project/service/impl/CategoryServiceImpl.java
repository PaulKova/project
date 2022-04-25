package com.amr.project.service.impl;

import com.amr.project.converter.mappers.CategoryMapper;
import com.amr.project.dao.CategoryRepository;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Order;
import com.amr.project.service.abstracts.CategoryService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailCategoryAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final MailSender mailSender;
    private final EmailCategoryAssistant emailCategoryAssistant;


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories, new CycleAvoidingMappingContext());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.getById(id);
        return categoryMapper.toDto(category, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto, new CycleAvoidingMappingContext());
        mailSender.send(emailCategoryAssistant.trackedEmailCategoryCreated(category));
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto, new CycleAvoidingMappingContext());
        mailSender.send(emailCategoryAssistant.trackedEmailCategoryUpdate(category));
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryMapper.toEntity(getCategoryById(id), new CycleAvoidingMappingContext());
        mailSender.send(emailCategoryAssistant.trackedEmailCategoryDelete(category));
        categoryRepository.deleteById(id);

    }
}
