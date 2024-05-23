package com.project.StoreManagement.controllers;

import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    public ResponseMessage createCategory(@RequestBody @Valid RequestMessage<Category> category) {
        return categoryServices.createCategory(category);
    }

    @PutMapping({"/{id}"})
    public ResponseMessage getCategoryById(@PathVariable @Valid Long id) {
        return categoryServices.getCategoryById(id);
    }

    @PostMapping({"/{id}"})
    public ResponseMessage updateCategory(@RequestBody @Valid RequestMessage<Category> category, @PathVariable Long id) {
        return categoryServices.updateCategory(category, id);
    }

    @GetMapping
    public List<Category> updateCategory() {
        return categoryServices.getAllCategory();
    }

    @DeleteMapping({"/{id}"})
    public ResponseMessage deleteArticle(@PathVariable Long id) {
        return categoryServices.deleteCategory(id);
    }

}
