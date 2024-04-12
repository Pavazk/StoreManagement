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

    /**
     * Metodo encargado de almacenar el articulo en la base de datos
     * @param article
     * @return articulo creado
     */
    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    /**
     * Metodo encargado de buscar el articulo por su id
     * @param id
     * @return articulo con el id correspondiente o null en caso de no encontrar ninguno
     */
    @Override
    public Article getArticleById(Long id) {
        Optional<Article> articleById = articleRepository.findById(id);
        return articleById.orElse(null);
    }

    /**
     * Metodo encargado de actualizar el articulo que corresponda con el id
     * @param newArticle
     * @param id
     * @return articulo actualizado o null en caso de no encontrar articulo con el id correspondiente
     */
    @Override
    public Article updateArticle(Article newArticle, Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            Article oldArticle = optionalArticle.get();
            if (newArticle.getArticleDescription() != null) {
                oldArticle.setArticleDescription(newArticle.getArticleDescription());
            }
            if (newArticle.getArticlePrice() != null) {
                oldArticle.setArticlePrice(newArticle.getArticlePrice());
            }
            if (newArticle.getArticleStock() != null) {
                oldArticle.setArticleStock(newArticle.getArticleStock());
            }
            if (newArticle.getArticleStatus() != null) {
                oldArticle.setArticleStatus(newArticle.getArticleStatus());
            }
            if (newArticle.getCategory() != null) {
                oldArticle.setCategory(newArticle.getCategory());
            }
            return articleRepository.save(oldArticle);
        } else {
            return null;
        }
    }

    /**
     * Metodo encargado de retornar todos los articulos
     * @return lista de articulos almacenados en la base de datos
     */
    @Override
    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }
}
