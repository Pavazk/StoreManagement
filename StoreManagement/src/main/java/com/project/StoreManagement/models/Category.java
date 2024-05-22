package com.project.StoreManagement.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;
    @NotBlank(message = "Por favor ingresar el nombre de la categoria")
    private String categoryName;
    private String categoryDescription;
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Article> listArticle;
}
