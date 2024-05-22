package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;

public interface ArticleServices {
    ResponseMessage createArticle(RequestMessage<Article> requestMessage);

/*    Article getArticleById(Long id);

    Article updateArticle(Article article, Long id);

    List<Article> getAllArticles();*/
}
