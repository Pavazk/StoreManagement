package com.project.StoreManagement.models;

import com.project.StoreManagement.models.enunm.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String articleName;
    private String articleDescription;
    private String articlePrice;
    private String articleStock;
    private Status articleStatus;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;
}
