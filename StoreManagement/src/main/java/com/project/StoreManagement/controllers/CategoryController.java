package com.project.StoreManagement.controllers;

import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryServices.createCategory(category);
    }

    @PutMapping({"/{id}"})
    public Category getCategoryById(@PathVariable Long id) {
        return categoryServices.getCategoryById(id);
    }

    @PostMapping({"/{id}"})
    public Category updateCategory(@RequestBody Category article, @PathVariable Long id) {
        return categoryServices.updateCategory(article, id);
    }

    @GetMapping
    public List<Category> updateCategory() {
        return categoryServices.getAllCategory();
    }

}