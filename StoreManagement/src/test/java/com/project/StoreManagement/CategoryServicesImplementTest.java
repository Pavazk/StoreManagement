package com.project.StoreManagement;

import com.project.StoreManagement.exceptions.AlreadyInUseException;
import com.project.StoreManagement.exceptions.NotFoundException;
import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.repository.CategoryRepository;
import com.project.StoreManagement.services.implementations.CategoryServicesImplement;
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

public class CategoryServicesImplementTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServicesImplement categoryServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        ResponseMessage response = categoryServices.createCategory(RequestMessage.<Category>builder().object(category).build());

        assertNotNull(response);
        assertEquals("Categoria creada correctamente", response.getMessage().get(0));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testGetCategoryById_Found() {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("Test Category");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        ResponseMessage response = categoryServices.getCategoryById(1L);

        assertNotNull(response);
        assertTrue(response.getMessage().get(0).contains("se encontro correctamente"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryServices.getCategoryById(1L);
        });

        assertEquals("Categoria no encontrada", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateCategory_Found() {
        Category oldCategory = new Category();
        oldCategory.setId(1L);
        oldCategory.setCategoryName("Old Name");

        Category newCategory = new Category();
        newCategory.setCategoryName("New Name");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(oldCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(oldCategory);

        ResponseMessage response = categoryServices.updateCategory(RequestMessage.<Category>builder().object(newCategory).build(), 1L);

        assertNotNull(response);
        assertTrue(response.getMessage().get(0).contains("correctamente actualizada"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("New Name", oldCategory.getCategoryName());

        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(oldCategory);
    }

    @Test
    public void testUpdateCategory_NotFound() {
        Category newCategory = new Category();

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryServices.updateCategory(RequestMessage.<Category>builder().object(newCategory).build(), 1L);
        });

        assertEquals("Categoria no encontrada", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllCategory() {
        Category category1 = new Category();
        Category category2 = new Category();
        List<Category> categories = List.of(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryServices.getAllCategory();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCategory_Found() {
        Category category = new Category();
        category.setId(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        ResponseMessage response = categoryServices.deleteCategory(1L);

        assertNotNull(response);
        assertTrue(response.getMessage().get(0).contains("eliminado correctamente"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    public void testDeleteCategory_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            categoryServices.deleteCategory(1L);
        });

        assertEquals("Categoria no encontrada", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteCategory_InUse() {
        Category category = new Category();
        category.setId(1L);
        category.setListArticle(List.of(new Article()));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        AlreadyInUseException exception = assertThrows(AlreadyInUseException.class, () -> {
            categoryServices.deleteCategory(1L);
        });

        assertEquals("Categoria en uso, no es posible eliminar", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
    }
}