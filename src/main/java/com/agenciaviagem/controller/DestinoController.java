package com.agenciaviagem.controller;

import com.agenciaviagem.dominio.dto.DestinoListDTO;
import com.agenciaviagem.dominio.entity.Destino;
import com.agenciaviagem.service.DestinoService;
import org.springframework.http.HttpStatus;
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
        Optional<Destino> destino = destinoService.buscarPorId(id);
        if (destino.isPresent()) {
            return new ResponseEntity<>(destino.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Destino(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.excluirDestino(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Void> avaliarDestino(@PathVariable Long id, @RequestParam int nota) {
        destinoService.avaliarDestino(id, nota);
        return ResponseEntity.ok().build();
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


