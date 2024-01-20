package com.example.usuarios.demo.usuarios.services;

import com.example.usuarios.demo.usuarios.exceptions.ServicesException;
import com.example.usuarios.demo.usuarios.models.entities.User;
import com.example.usuarios.demo.usuarios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?>  findById(Long id) throws ServicesException {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()){
                return ResponseEntity.ok(userOptional.get());
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception ex){
            throw new ServicesException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al realizar la peticion",
                    "no se pudo realizar la busqueda");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(User user) throws ServicesException {

        try {
            if(IsObjectValid(user, "CREATE")){
                return ResponseEntity.ok(userRepository.save(user));
            }else{
                return new ResponseEntity<>(new ServicesException(HttpStatus.BAD_REQUEST, "Error al realizar la peticion",
                        "no se permiten datos vacios"), null, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(new ServicesException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al realizar la peticion",
                    "no se pudo Crear el objeto"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean IsObjectValid(User user, String action){
        switch (action){
            case "CREATE":
                if(user.getEmail().isEmpty() || user.getUsername().isEmpty() || user.getPassword().isEmpty()){
                    return false;
                }else{
                    return true;
                }
            case "UPDATE":
                if(user.getEmail().isEmpty() || user.getUsername().isEmpty()){
                    return false;
                }else{
                    return true;
                }
            default:
                return false;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> remove(Long id) {


        try{
            Optional<User> userToRemove = userRepository.findById(id);

            if (userToRemove.isPresent()) {
                userRepository.deleteById(id);
                return ResponseEntity.ok("Se elimin\u00F3 con \u00E9xito el usuario.");
            }else{
                return new ResponseEntity<>(new ServicesException(HttpStatus.NO_CONTENT, "Error al realizar la peticion",
                        "no se encontro el usuario"), null, HttpStatus.NO_CONTENT);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(new ServicesException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al realizar la peticion",
                    "Error al eliminar"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?>  update(User user) {
        try {
            if(IsObjectValid(user,"UPDATE")){
                Optional<User> userDb = userRepository.findById(user.getId());

                if (userDb.isPresent()) {
                    userDb.get().setUsername(user.getUsername());
                    userDb.get().setEmail(user.getEmail());
                    return ResponseEntity.ok(userRepository.save(user));
                }else{
                    return new ResponseEntity<>(new ServicesException(HttpStatus.NO_CONTENT, "Error al realizar la peticion",
                            "no se encontro el usuario"), null, HttpStatus.NO_CONTENT);
                }
            }else{
                return new ResponseEntity<>(new ServicesException(HttpStatus.BAD_REQUEST, "Error al realizar la peticion",
                        "no se permiten datos vacios"), null, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(new ServicesException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al realizar la peticion",
                    "no se pudo Actualizar el objeto"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
