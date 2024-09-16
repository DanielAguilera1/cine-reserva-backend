package com.cine_reserva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine_reserva_backend.model.table.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findAllByGeneroIgnoreCase(String genero);
}
