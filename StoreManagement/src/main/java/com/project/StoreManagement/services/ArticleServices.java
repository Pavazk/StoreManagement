package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;

import java.util.List;

public interface ArticleServices {
    ResponseMessage createArticle(RequestMessage<Article> requestMessage);

    ResponseMessage getArticleById(Long id);

    ResponseMessage updateArticle(RequestMessage<Article>articleRequestMessage, Long id);

    List<Article> getAllArticles();

    ResponseMessage deleteArticle(Long id);
}
