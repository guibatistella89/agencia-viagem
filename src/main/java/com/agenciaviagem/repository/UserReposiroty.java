package com.agenciaviagem.repository;

import com.agenciaviagem.dominio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository

public interface UserReposiroty extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByEmail(String email);

    List<UserEntity> findAllByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    List<UserEntity> retornarUsernames(@Param("username") String username);
}