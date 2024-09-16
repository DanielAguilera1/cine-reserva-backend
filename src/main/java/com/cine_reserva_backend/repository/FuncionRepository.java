package com.cine_reserva_backend.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cine_reserva_backend.model.document.Funcion;

public interface FuncionRepository extends MongoRepository<Funcion, String> {
    boolean existsBySalaIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
            Integer salaId, Date fechaInicio, Date fechaFin);

    Optional<List<Funcion>> findByFechaInicioBetween(Date inicioDelDia, Date finDelDia);
}
