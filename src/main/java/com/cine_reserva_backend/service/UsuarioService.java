package com.cine_reserva_backend.service;

import com.cine_reserva_backend.model.dto.RegisterDTO;
import com.cine_reserva_backend.model.table.Usuario;
import com.cine_reserva_backend.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> ListaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorID(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void EliminarUsuarioPorID(long id) throws Exception {
        Usuario usuario = obtenerUsuarioPorID(id);
        if (usuario == null) throw new Exception("no encontrado");
        usuarioRepository.delete(usuario);
    }

    public void registrarUsuario(RegisterDTO registerDTO) {
        usuarioRepository.save(Usuario.builder()
                .email(registerDTO.getEmail())
                .nombre(registerDTO.getNombre())
                .password(new BCryptPasswordEncoder().encode(registerDTO.getPassword()))
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
    }
}
