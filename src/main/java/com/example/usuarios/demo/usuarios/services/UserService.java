package com.example.usuarios.demo.usuarios.services;

import com.example.usuarios.demo.usuarios.exceptions.ServicesException;
import com.example.usuarios.demo.usuarios.models.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    ResponseEntity<?> findById(Long id) throws ServicesException;

    ResponseEntity<?> save(User user) throws ServicesException;

    ResponseEntity<?> remove(Long id);

    ResponseEntity<?>  update(User user);


}
