package com.agenciaviagem.config;

import com.agenciaviagem.dominio.entity.UserEntity;
import com.agenciaviagem.repository.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    private UserReposiroty userReposiroty;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userReposiroty.findByUsername(username);
        return userEntity.map(AutenticatedUser::new).orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " Não encontrado"));
    }
}
