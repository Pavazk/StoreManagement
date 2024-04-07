package com.project.StoreManagement.services;

import com.project.StoreManagement.models.Category;
import com.project.StoreManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImplement implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Metodo encargado de guardar en la base de datos la categoria
     * @param category
     * @return la categoria anteriormente creada
     */
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Metodo encargado de buscar la categoria por su id
     * @param id
     * @return la categoria con el id correspondiente o null en caso de no encontrar ninguno
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Metodo encargado de actualizar la categoria que corresponda con el id
     * @param newCategory
     * @param id
     * @return categoria actualizada o null en caso de no encontrar categoria con el id correspondiente
     */
    @Override
    public Category updateCategory(Category newCategory, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category oldCategory = optionalCategory.get();
            if (newCategory.getCategoryName() != null) {
                oldCategory.setCategoryName(newCategory.getCategoryName());
            }
            if (newCategory.getCategoryDescription() != null) {
                oldCategory.setCategoryDescription(newCategory.getCategoryDescription());
            }
            return categoryRepository.save(oldCategory);
        } else {
            return null;
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
}
