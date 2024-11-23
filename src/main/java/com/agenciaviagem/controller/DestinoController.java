package com.agenciaviagem.controller;

import com.agenciaviagem.model.Destino;
import com.agenciaviagem.model.DestinoListDTO;
import com.agenciaviagem.service.DestinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {
    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        return ResponseEntity.ok(destinoService.cadastrarDestino(destino));
    }

    @GetMapping
    public ResponseEntity<List<DestinoListDTO>> listarDestinos() {
        return ResponseEntity.ok(destinoService.listarDestinos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarPorId(@PathVariable Long id) {
        return destinoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.excluirDestino(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Void> avaliarDestino(@PathVariable Long id, @RequestParam int nota) {
        destinoService.avaliarDestino(id, nota);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        Optional<Destino> destinoExistente = destinoService.buscarPorId(id);
        if (destinoExistente.isPresent()) {
            destino.setId(id);
            return ResponseEntity.ok(destinoService.atualizarDestino(destino));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    // Endpoint para pesquisar destinos por nome ou localização
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam(required = false) String nome, 
                                                           @RequestParam(required = false) String localizacao) {
        List<Destino> resultados = destinoService.pesquisarDestinos(nome, localizacao);
        return ResponseEntity.ok(resultados);
    }
}


