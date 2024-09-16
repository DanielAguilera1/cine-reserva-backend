package com.cine_reserva_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine_reserva_backend.model.table.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
