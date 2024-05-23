package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Article;
import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImplement implements CategoryServices {
    ArticleServicesImplement articleServicesImplement = new ArticleServicesImplement();
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Metodo encargado de guardar en la base de datos la categoria
     * @param category
     * @return la categoria anteriormente creada
     */
    @Override
    public ResponseMessage createCategory(RequestMessage<Category> category) {
        categoryRepository.save(category.getObject());
        return articleServicesImplement.createResponse("Categoria creada correctamente", HttpStatus.OK);
    }

    /**
     * Metodo encargado de buscar la categoria por su id
     * @param id
     * @return la categoria con el id correspondiente o null en caso de no encontrar ninguno
     */
    @Override
    public ResponseMessage getCategoryById(Long id) {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isPresent()){
            return articleServicesImplement.createResponse("Categoria " + categoryById.get().getCategoryName() + " se encontro correctamente", HttpStatus.OK);
        } else {
            return articleServicesImplement.createResponse("No existe categoria con ese id", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo encargado de actualizar la categoria que corresponda con el id
     * @param newCategory
     * @param id
     * @return categoria actualizada o null en caso de no encontrar categoria con el id correspondiente
     */
    @Override
    public ResponseMessage updateCategory(RequestMessage<Category> newCategory, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category oldCategory = optionalCategory.get();
            if (newCategory.getObject().getCategoryName() != null) {
                oldCategory.setCategoryName(newCategory.getObject().getCategoryName());
            }
            if (newCategory.getObject().getCategoryDescription() != null) {
                oldCategory.setCategoryDescription(newCategory.getObject().getCategoryDescription());
            }
            categoryRepository.save(oldCategory);
            return articleServicesImplement.createResponse("Articulo con id: " + optionalCategory.get().getId() + " correctamente actualizado", HttpStatus.OK);
        } else {
            return articleServicesImplement.createResponse("No se encontro el articulo a actualizar", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metodo encargado de retornar todos las categorias
     * @return lista de categorias almacenadas en la base de datos
     */
    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public ResponseMessage deleteCategory(Long id) {
        Optional<Category> optionalArticle = categoryRepository.findById(id);

        if (optionalArticle.isPresent() && optionalArticle.get().getListArticle().isEmpty()) {
            categoryRepository.delete(optionalArticle.get());
            return articleServicesImplement.createResponse("Articulo con id: " + optionalArticle.get().getId() + " eliminado correctamente", HttpStatus.OK);
        } else {
            return articleServicesImplement.createResponse("Articulo a eliminar no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
