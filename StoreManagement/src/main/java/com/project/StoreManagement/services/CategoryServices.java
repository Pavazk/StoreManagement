package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryServices {

    ResponseMessage createCategory(RequestMessage<Category> category);

    ResponseMessage getCategoryById(Long id);

    ResponseMessage updateCategory(RequestMessage<Category> newCategory, Long id);

    List<Category> getAllCategory();

    ResponseMessage deleteCategory(Long id);
}
