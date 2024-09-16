package com.cine_reserva_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cine_reserva_backend.model.table.Usuario;
import com.cine_reserva_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> ListaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario ObtenerFuncionPorID(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void AgregarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void EliminarUsuarioPorID(long id) {
        usuarioRepository.deleteById(id);
    }
}
