package com.project.StoreManagement.services;


import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServicesImplement implements ArticleServices {
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Metodo encargado de almacenar el articulo en la base de datos
     *
     * @param requestMessage
     * @return articulo creado
     */
    @Override
    public ResponseMessage createArticle(RequestMessage<Article> requestMessage) {
        articleRepository.save((Article)requestMessage.getObject());
        return setResponse("Creado correctamente", HttpStatus.OK);
    }

    /**
     * Metodo encargado de buscar el articulo por su id
     *
     * @param id
     * @return articulo con el id correspondiente o null en caso de no encontrar ninguno
     */
    @Override
    public ResponseMessage getArticleById(Long id) {
        System.out.println("paso por aca");
        Optional<Article> articleById = articleRepository.findById(id);
        if (articleById.isPresent()) {
            return setResponse("Artículo encontrado: " + articleById.get().getArticleName(), HttpStatus.OK);
        } else {
            return setResponse("Artículo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /**

     *//**
     * Metodo encargado de actualizar el articulo que corresponda con el id
     * @param newArticle
     * @param id
     * @return articulo actualizado o null en caso de no encontrar articulo con el id correspondiente
     */
    @Override
    public ResponseMessage updateArticle(RequestMessage<Article> newArticle, Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            Article oldArticle = optionalArticle.get();
            if (newArticle.getObject().getArticleName() != null) {
                oldArticle.setArticleName(newArticle.getObject().getArticleName());
            }
            if (newArticle.getObject().getArticleDescription() != null) {
                oldArticle.setArticleDescription(newArticle.getObject().getArticleDescription());
            }
            if (newArticle.getObject().getArticlePrice() != null) {
                oldArticle.setArticlePrice(newArticle.getObject().getArticlePrice());
            }
            if (newArticle.getObject().getArticleStock() != null) {
                oldArticle.setArticleStock(newArticle.getObject().getArticleStock());
            }
            if (newArticle.getObject().getArticleStatus() != null) {
                oldArticle.setArticleStatus(newArticle.getObject().getArticleStatus());
            }
            if (newArticle.getObject().getCategory() != null) {
                oldArticle.setCategory(newArticle.getObject().getCategory());
            }
            System.out.println("aa" + oldArticle.getArticleName());
            System.out.println(oldArticle.getArticleDescription());
            articleRepository.save(oldArticle);
            return setResponse("Articulo con id " + optionalArticle.get().getId() + " actualizado correctamente", HttpStatus.OK);
        } else {
            return null;
        }
    }

    /*

    /**
     * Metodo encargado de retornar todos los articulos
     *
     * @return lista de articulos almacenados en la base de datos
     */
    @Override
    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    @Override
    public ResponseMessage deleteArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            articleRepository.delete(optionalArticle.get());
            return setResponse("Articulo con id: " + optionalArticle.get().getId() + " eliminado correctamente", HttpStatus.OK);
        } else {
            return setResponse("Articulo a eliminar no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public String getDate() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.n");
        return fechaHoraActual.format(formatter);
    }

    public ResponseMessage setResponse(String message, HttpStatus httpStatus) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setDate(getDate());
        responseMessage.setMessage(message);
        responseMessage.setStatusCode(String.valueOf(httpStatus.value()));
        return responseMessage;
    }
}
