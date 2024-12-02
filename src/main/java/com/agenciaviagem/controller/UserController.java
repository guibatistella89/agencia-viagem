package com.agenciaviagem.controller;

import com.agenciaviagem.dominio.entity.UserEntity;
import com.agenciaviagem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public ResponseEntity<UserEntity> cadastrarUsuario(@RequestBody UserEntity user) {
        UserEntity usuarioCadastrado = userService.cadastraUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> listarUsuarios() {
        return ResponseEntity.ok(userService.listaUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> buscarPorId(@PathVariable Long id) {
        Optional<UserEntity> usuario = userService.buscarPorId(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        userService.deletaUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> atualizarUsuario(@PathVariable Long id, @RequestBody UserEntity userAtualizado) {
        Optional<UserEntity> usuario = userService.buscarPorId(id);
        if (usuario.isPresent()) {
            userAtualizado.setId(id);
            return ResponseEntity.ok(userService.atualizaUsuario(userAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
