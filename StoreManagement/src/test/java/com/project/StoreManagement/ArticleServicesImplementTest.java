package com.project.StoreManagement;
import com.project.StoreManagement.exceptions.NotFoundException;
import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.repository.ArticleRepository;
import com.project.StoreManagement.services.ArticleServicesImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArticleServicesImplementTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServicesImplement articleServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateArticle() {
        Article article = new Article();

        when(articleRepository.save(any(Article.class))).thenReturn(article);

        ResponseMessage response = articleServices.createArticle(RequestMessage.<Article>builder().object(article).build());

        assertNotNull(response);
        assertEquals("Creado correctamente", response.getMessage().get(0));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testGetArticleById_Found() {
        Article article = new Article();
        article.setId(1L);
        article.setArticleName("Test Article");

        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        ResponseMessage response = articleServices.getArticleById(1L);

        assertNotNull(response);
        assertTrue(response.getMessage().get(0).contains("Se encontro el articulo"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        verify(articleRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetArticleById_NotFound() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            articleServices.getArticleById(1L);
        });

        assertEquals("Articulo no encontrado", exception.getMessage());
        verify(articleRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateArticle_Found() {
        Article oldArticle = new Article();
        oldArticle.setId(1L);
        oldArticle.setArticleName("Old Name");

        Article newArticle = new Article();
        newArticle.setArticleName("New Name");


        when(articleRepository.findById(1L)).thenReturn(Optional.of(oldArticle));
        when(articleRepository.save(any(Article.class))).thenReturn(oldArticle);

        ResponseMessage response = articleServices.updateArticle(RequestMessage.<Article>builder().object(newArticle).build(), 1L);

        assertNotNull(response);
        assertTrue(response.getMessage().get(0).contains("actualizado correctamente"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("New Name", oldArticle.getArticleName());

        verify(articleRepository, times(1)).findById(1L);
        verify(articleRepository, times(1)).save(oldArticle);
    }

    @Test
    public void testUpdateArticle_NotFound() {
        Article newArticle = new Article();

        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            articleServices.updateArticle(RequestMessage.<Article>builder().object(newArticle).build(), 1L);
        });

        assertEquals("Articulo no encontrado", exception.getMessage());
        verify(articleRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllArticles() {
        Article article1 = new Article();
        Article article2 = new Article();
        List<Article> articles = List.of(article1, article2);

        when(articleRepository.findAll()).thenReturn(articles);

        List<Article> result = articleServices.getAllArticles();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(articleRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteArticle_Found() {
        Article article = new Article();
        article.setId(1L);

        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        ResponseMessage response = articleServices.deleteArticle(1L);

        assertNotNull(response);
        assertTrue(response.getMessage().get(0).contains("eliminado correctamente"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        verify(articleRepository, times(1)).findById(1L);
        verify(articleRepository, times(1)).delete(article);
    }

    @Test
    public void testDeleteArticle_NotFound() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            articleServices.deleteArticle(1L);
        });

        assertEquals("Articulo no encontrado", exception.getMessage());
        verify(articleRepository, times(1)).findById(1L);
    }
}
