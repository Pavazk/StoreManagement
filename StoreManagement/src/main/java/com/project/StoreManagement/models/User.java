package com.project.StoreManagement.models;

import com.project.StoreManagement.models.enunm.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.passwordUser;
    }

    @Override
    public String getUsername() {
        return this.emailUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
