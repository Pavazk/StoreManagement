package com.project.StoreManagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.StoreManagement.models.enunm.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;
    @NotBlank(message = "Por favor ingrese el nombre del articulo")
    private String articleName;
    private String articleDescription;
    @NotBlank(message = "Por favor ingrese el precio del articulo")
    private String articlePrice;
    @NotBlank(message = "Por favor ingrese el stock del articulo")
    private String articleStock;
    private Status articleStatus;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Category category;
}
