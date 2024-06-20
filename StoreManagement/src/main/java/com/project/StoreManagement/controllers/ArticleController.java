package com.project.StoreManagement.controllers;

import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.services.interfaces.ArticleServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    private final ArticleServices articleServices;

    @Autowired
    public ArticleController(ArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @PostMapping
    public ResponseMessage createArticle(@Valid @RequestBody RequestMessage<Article> requestMessage) {
        return articleServices.createArticle(requestMessage);
    }

   @PutMapping({"/{id}"})
    public ResponseMessage getArticleById(@PathVariable Long id) {
        return articleServices.getArticleById(id);
    }


    @PostMapping({"/{id}"})
    public ResponseMessage updateArticle(@RequestBody RequestMessage<Article> articleRequestMessage, @PathVariable Long id) {
        return articleServices.updateArticle(articleRequestMessage, id);
    }

    @GetMapping
    public List<Article> getAllArticle() {
        return articleServices.getAllArticles();
    }

    @DeleteMapping({"/{id}"})
    public ResponseMessage deleteArticle(@PathVariable Long id) {
        return articleServices.deleteArticle(id);
    }

}
