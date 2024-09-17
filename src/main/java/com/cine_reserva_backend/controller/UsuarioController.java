package com.cine_reserva_backend.controller;

import com.cine_reserva_backend.model.table.Usuario;
import com.cine_reserva_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Usuario usuario = usuarioService.ObtenerFuncionPorID(id);
        if (usuarioService.ObtenerFuncionPorID(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

}
