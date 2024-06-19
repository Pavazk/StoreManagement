package com.project.StoreManagement.services.implementations;

import com.project.StoreManagement.exceptions.AlreadyInUseException;
import com.project.StoreManagement.exceptions.NotFoundException;
import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.repository.CategoryRepository;
import com.project.StoreManagement.services.interfaces.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.project.StoreManagement.services.implementations.ArticleServicesImplement.createResponse;

@Service
public class CategoryServicesImplement implements CategoryServices {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServicesImplement(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Metodo encargado de guardar en la base de datos la categoria
     * @param category
     * @return la categoria anteriormente creada
     */
    @Override
    public ResponseMessage createCategory(RequestMessage<Category> category) {
        categoryRepository.save(category.getObject());
        return createResponse("Categoria creada correctamente");
    }

    /**
     * Metodo encargado de buscar la categoria por su id
     * @param id
     * @return la categoria con el id correspondiente o null en caso de no encontrar ninguno
     */
    @Override
    public ResponseMessage getCategoryById(Long id) {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isEmpty()) {
            throw new NotFoundException("Categoria no encontrada");
        }
        return createResponse("Categoria " + categoryById.get().getCategoryName() + " se encontro correctamente");
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
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Categoria no encontrada");
        }
        Category oldCategory = optionalCategory.get();
        if (newCategory.getObject().getCategoryName() != null) {
            oldCategory.setCategoryName(newCategory.getObject().getCategoryName());
        }
        if (newCategory.getObject().getCategoryDescription() != null) {
            oldCategory.setCategoryDescription(newCategory.getObject().getCategoryDescription());
        }
        categoryRepository.save(oldCategory);
        return createResponse("Categoria con id: " + optionalCategory.get().getId() + " correctamente actualizada");
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
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Categoria no encontrada");
        }
        if (optionalCategory.get().getListArticle() != null && !optionalCategory.get().getListArticle().isEmpty()) {
            throw new AlreadyInUseException("Categoria en uso, no es posible eliminar");
        }
        categoryRepository.delete(optionalCategory.get());
        return createResponse("Categoria con id: " + optionalCategory.get().getId() + " eliminado correctamente");
    }
}