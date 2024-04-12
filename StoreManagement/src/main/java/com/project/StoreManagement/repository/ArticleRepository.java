package com.project.StoreManagement.repository;


import com.project.StoreManagement.models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
