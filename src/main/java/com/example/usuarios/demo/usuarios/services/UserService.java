package com.example.usuarios.demo.usuarios.services;

import com.example.usuarios.demo.usuarios.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);

    User save(User user);

    void remove(Long id);

    User update(User user);


}
