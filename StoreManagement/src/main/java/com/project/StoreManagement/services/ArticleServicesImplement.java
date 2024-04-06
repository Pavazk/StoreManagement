package com.project.StoreManagement.services;


import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServicesImplement implements ArticleServices {
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> articleById = articleRepository.findById(id);
        if (articleById.isEmpty()){
            return null;
        }

        return articleById.get();
    }

    @Override
    public Article updateArticle(Article article, Long id) {
        Optional<Article> updateArticle = articleRepository.findById(id);
        if (updateArticle.isEmpty()){
            return null;
        }
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }
}
