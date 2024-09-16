package com.cine_reserva_backend.repository;

import com.cine_reserva_backend.model.table.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findAllByGeneroIgnoreCase(String genero);
}
