package com.cine_reserva_backend.repository;

import com.cine_reserva_backend.model.table.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
