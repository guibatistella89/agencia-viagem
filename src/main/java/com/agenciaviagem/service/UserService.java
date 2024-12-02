package com.agenciaviagem.service;

import com.agenciaviagem.dominio.entity.UserEntity;
import com.agenciaviagem.repository.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserReposiroty userReposiroty;

    public List<UserEntity> listaUsuarios() {
        return userReposiroty.findAll();
    }

    public Optional<UserEntity> buscarPorId(Long id) {
        return userReposiroty.findById(id);
    }

    public UserEntity cadastraUsuario(UserEntity user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaEncriptada = passwordEncoder.encode(user.getPassword()); // Encriptando a senha

        user.setPassword(senhaEncriptada);

        return userReposiroty.save(user);
    }

    public UserEntity atualizaUsuario(UserEntity userAtualizado) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaEncriptada = passwordEncoder.encode(userAtualizado.getPassword());
        userAtualizado.setPassword(senhaEncriptada);
        return userReposiroty.save(userAtualizado);
    }

    public void deletaUsuario(Long id) {
        userReposiroty.deleteById(id);
    }
}
