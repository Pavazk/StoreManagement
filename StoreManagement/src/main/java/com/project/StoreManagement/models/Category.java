package com.project.StoreManagement.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String categoryName;
    String categoryDescription;
    @OneToMany(mappedBy = "category")
    private List<Article> listArticle;
}
