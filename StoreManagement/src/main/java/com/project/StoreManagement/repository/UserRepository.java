package com.project.StoreManagement.repository;

import com.project.StoreManagement.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailUser(String email);
}
