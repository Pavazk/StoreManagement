package com.project.StoreManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Por favor ingresar el nombre del usuario")
    private String nameUser;
    @NotBlank(message = "Por favor ingresar el email del usuario")
    private String emailUser;
    @NotBlank(message = "Por favor ingresar el numero telefonico del usuario")
    private String phoneNumberUser;
    @NotBlank(message = "Por favor ingresar el genero del usuario")
    private String genderUser;
    @NotBlank(message = "Por favor ingresar la direccion del usuario")
    private String addressUser;
    @NotBlank(message = "Por favor ingresar el documento del usuario")
    private String documentUser;
    @NotBlank(message = "Por favor ingresar la contraseña del usuario")
    private String passwordUser;
}
