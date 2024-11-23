package com.agenciaviagem.service;

import com.agenciaviagem.model.Destino;
import com.agenciaviagem.model.DestinoListDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinoService {
    private final List<Destino> destinos = new ArrayList<>();


    public Destino cadastrarDestino(Destino destino) {
        destino.setId((long) (destinos.size() + 1));
        destinos.add(destino);
        return destino;
    }

    public List<DestinoListDTO> listarDestinos() {
        return destinos.stream()
                .map(destino -> new DestinoListDTO(destino.getId(), destino.getNome())) // Mapeando para DestinoListDTO
                .collect(Collectors.toList());
    }

    public Optional<Destino> buscarPorId(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public void excluirDestino(Long id) {
        destinos.removeIf(d -> d.getId().equals(id));
    }

    public void avaliarDestino(Long id, int nota) {
        buscarPorId(id).ifPresent(destino -> {
            destino.setMediaAvaliacao(
                    (destino.getMediaAvaliacao() * destino.getTotalAvaliacoes() + nota)
                            / (destino.getTotalAvaliacoes() + 1));
            destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);
        });
    }

    public Destino atualizarDestino(Destino destinoAtualizado) {
        buscarPorId(destinoAtualizado.getId()).ifPresent(destino -> {
            destino.setNome(destinoAtualizado.getNome());
            destino.setLocalizacao(destinoAtualizado.getLocalizacao());
            destino.setDescricao(destinoAtualizado.getDescricao());
            destino.setMediaAvaliacao(destinoAtualizado.getMediaAvaliacao());
            destino.setTotalAvaliacoes(destinoAtualizado.getTotalAvaliacoes());
        });
        return destinoAtualizado;
    }

    // Método para pesquisar destinos por nome ou localização
    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinos.stream()
                .filter(destino -> (nome == null || destino.getNome().toLowerCase().contains(nome.toLowerCase())) &&
                                   (localizacao == null || destino.getLocalizacao().toLowerCase().contains(localizacao.toLowerCase())))
                .collect(Collectors.toList());
    }
}
