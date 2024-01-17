package com.example.usuarios.demo.usuarios.services;

import com.example.usuarios.demo.usuarios.models.entities.User;
import com.example.usuarios.demo.usuarios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Optional<User> userToRemove = findById(id);

        if (userToRemove.isPresent()) {
            userRepository.deleteById(id);
        }else{
            //TODO: lanzar excepcion de que no se encontro 204
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        if(isValidUser(user)){
            Optional<User> userDb = this.findById(user.getId());

            if (userDb.isPresent()) {
                userDb.get().setUsername(user.getUsername());
                userDb.get().setEmail(user.getEmail());
                return save(userDb.get());
            }else{
                //TODO:lanzar excepcion de que no se encontro ese usuario
            }
        }

        //TODO:lanzar excepcion de que no viene el id

        return null;
    }

    public boolean isValidUser(User user){
        return true;
    }


}
