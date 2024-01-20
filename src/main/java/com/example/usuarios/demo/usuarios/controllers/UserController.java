package com.example.usuarios.demo.usuarios.controllers;

import com.example.usuarios.demo.usuarios.exceptions.ServicesException;
import com.example.usuarios.demo.usuarios.models.entities.User;
import com.example.usuarios.demo.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return userService.findById(id);
        } catch (ServicesException ex) {
            return new ResponseEntity<>(new ServicesException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al realizar la peticion",
                    "no se pudo realizar la busqueda"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) throws ServicesException {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user) {
        return userService.update(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
       return userService.remove(id);
    }
}
