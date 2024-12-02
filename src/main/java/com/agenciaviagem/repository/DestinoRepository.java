package com.agenciaviagem.repository;

import com.agenciaviagem.dominio.entity.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    // Método que busca destinos por nome e localização
    List<Destino> findAllByNomeContainingIgnoreCaseOrLocalizacaoContainingIgnoreCase(String nome, String local);
}