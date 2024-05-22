package com.project.StoreManagement.controllers;

import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.services.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    @Autowired
    private ArticleServices articleServices;

    @PostMapping
    public ResponseMessage createArticle(@RequestBody RequestMessage<Article> requestMessage) {
        System.out.println("paso por aca3");
        return articleServices.createArticle(requestMessage);
    }

   @PutMapping({"/{id}"})
    public ResponseMessage getArticleById(@PathVariable Long id) {
        return articleServices.getArticleById(id);
    }

    /*

    @PostMapping({"/{id}"})
    public Article updateArticle(@RequestBody Article article, @PathVariable Long id) {
        return articleServices.updateArticle(article, id);
    }

    @GetMapping
    public List<Article> getAllArticle() {
        return articleServices.getAllArticles();
    }*/

}
