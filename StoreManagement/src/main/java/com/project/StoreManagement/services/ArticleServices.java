package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Article;

import java.util.List;

public interface ArticleServices {
    Article createArticle(Article article);

    Article getArticleById(Long id);

    Article updateArticle(Article article, Long id);

    List<Article> getAllArticles();
}
