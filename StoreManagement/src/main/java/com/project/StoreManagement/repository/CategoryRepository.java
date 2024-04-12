package com.project.StoreManagement.repository;

import com.project.StoreManagement.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
