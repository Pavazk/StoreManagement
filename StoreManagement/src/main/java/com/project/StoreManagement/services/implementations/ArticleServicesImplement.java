package com.project.StoreManagement.services.implementations;

import com.project.StoreManagement.exceptions.NotFoundException;
import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.repository.ArticleRepository;
import com.project.StoreManagement.services.interfaces.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class ArticleServicesImplement implements ArticleServices {

    @Autowired
    public ArticleServicesImplement(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    private final ArticleRepository articleRepository;

    /**
     * Metodo encargado de almacenar el articulo en la base de datos
     *
     * @param requestMessage
     * @return articulo creado
     */
    @Override
    public ResponseMessage createArticle(RequestMessage<Article> requestMessage) {
        articleRepository.save(requestMessage.getObject());
        return createResponse("Creado correctamente");
    }

    /**
     * Metodo encargado de buscar el articulo por su id
     *
     * @param id
     * @return articulo con el id correspondiente
     */
    @Override
    public ResponseMessage getArticleById(Long id) {
        Optional<Article> articleById = articleRepository.findById(id);
        if (articleById.isEmpty()) {
            throw new NotFoundException("Articulo no encontrado");
        }
        return createResponse("Se encontro el articulo: " + articleById.get().getArticleName());
    }

    /**
     * Metodo encargado de actualizar el articulo que corresponda con el id
     *
     * @param newArticle
     * @param id
     * @return articulo actualizado o null en caso de no encontrar articulo con el id correspondiente
     */
    @Override
    public ResponseMessage updateArticle(RequestMessage<Article> newArticle, Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            throw new NotFoundException("Articulo no encontrado");
        }
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
        articleRepository.save(oldArticle);
        return createResponse("Articulo con id " + optionalArticle.get().getId() + " actualizado correctamente");
    }

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

        if (optionalArticle.isEmpty()) {
            throw new NotFoundException("Articulo no encontrado");
        }
        articleRepository.delete(optionalArticle.get());
        return createResponse("Articulo con id: " + optionalArticle.get().getId() + " eliminado correctamente");
    }

    public static ResponseMessage createResponse(String message) {
        return ResponseMessage.builder()
                .date(LocalDate.now())
                .message(List.of(message))
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
