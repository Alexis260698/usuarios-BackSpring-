package com.example.usuarios.demo.usuarios.repositories;

import com.example.usuarios.demo.usuarios.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
