package com.cine_reserva_backend.repository;

import com.cine_reserva_backend.model.table.Tiquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiqueteRepository extends JpaRepository<Tiquete, Long> {
}
