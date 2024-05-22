package com.project.StoreManagement.controllers;

import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.services.ArticleServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    @Autowired
    private ArticleServices articleServices;

    @PostMapping
    public Article createArticle(@RequestBody @Valid Article article) {
        return articleServices.createArticle(article);
    }

    @PutMapping({"/{id}"})
    public Article getArticleById(@PathVariable @Valid Long id) {
        return articleServices.getArticleById(id);
    }

    @PostMapping({"/{id}"})
    public Article updateArticle(@RequestBody @Valid Article article, @PathVariable Long id) {
        return articleServices.updateArticle(article, id);
    }

    @GetMapping
    public List<Article> getAllArticle() {
        return articleServices.getAllArticles();
    }

}
