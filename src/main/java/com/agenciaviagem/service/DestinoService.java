package com.agenciaviagem.service;

import com.agenciaviagem.dominio.dto.DestinoListDTO;
import com.agenciaviagem.dominio.entity.Destino;

import com.agenciaviagem.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinoService {

    //private final List<Destino> destinos = new ArrayList<>();
    @Autowired
    private DestinoRepository destinoRepository;


    public Destino cadastrarDestino(Destino destino) {
        destinoRepository.save(destino);
        return destino;
    }

    public List<DestinoListDTO> listarDestinos() {
        return destinoRepository.findAll().stream()
                .map(destino -> new DestinoListDTO(destino.getId(), destino.getNome()))
                .collect(Collectors.toList());
    }

    public Optional<Destino> buscarPorId(Long id) {
        return destinoRepository.findById(id);
    }

    public void excluirDestino(Long id) {
        destinoRepository.deleteById(id);
    }

    public void avaliarDestino(Long id, int nota) {
        Destino destino = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        double novaMedia = (destino.getMediaAvaliacao() * destino.getTotalAvaliacoes() + nota)
                / (destino.getTotalAvaliacoes() + 1);

        destino.setMediaAvaliacao(novaMedia);
        destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);

        destinoRepository.save(destino);
    }


    public Destino atualizarDestino(Destino destinoAtualizado) {
        destinoRepository.save(destinoAtualizado);
        return destinoAtualizado;
    }

    // Método para pesquisar destinos por nome ou localização
    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinoRepository.findAllByNomeContainingIgnoreCaseOrLocalizacaoContainingIgnoreCase(nome, localizacao);
    }
}
