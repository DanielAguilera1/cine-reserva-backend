package com.cine_reserva_backend.web.controller;

import com.cine_reserva_backend.model.table.Usuario;
import com.cine_reserva_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> ListaUsuarios() {
        return ResponseEntity.ok(usuarioService.ListaUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorID(@PathVariable long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorID(id);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarUsuarioPorID(@PathVariable long id) {
        try {
            usuarioService.EliminarUsuarioPorID(id);
            return ResponseEntity.ok("Usuario Borrado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
