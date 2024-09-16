package com.cine_reserva_backend.repository;

import com.cine_reserva_backend.model.table.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
