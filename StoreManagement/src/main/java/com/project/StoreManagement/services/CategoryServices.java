package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Category;

import java.util.List;

public interface CategoryServices {

    Category createCategory(Category category);

    Category getCategoryById(Long id);

    Category updateCategory(Category category, Long id);

    List<Category> getAllCategory();
}
