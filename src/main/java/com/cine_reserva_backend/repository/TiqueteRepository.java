package com.cine_reserva_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine_reserva_backend.model.table.Tiquete;

public interface TiqueteRepository extends JpaRepository<Tiquete, Long> {
}
