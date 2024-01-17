package com.example.usuarios.demo.usuarios.controllers;

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
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);

        return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get()) : ResponseEntity.noContent().build();

    }

    @PostMapping
    public ResponseEntity<?>  create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(user));
        //TODO: agregar manejo de excepciones
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        userService.remove(id);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
