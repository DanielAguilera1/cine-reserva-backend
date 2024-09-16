package com.cine_reserva_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cine_reserva_backend.model.table.Sala;
import com.cine_reserva_backend.repository.SalaRepository;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> ListaSalas() {
        return salaRepository.findAll();
    }

    public Sala ObtenerSalaPorID(int id) {
        return salaRepository.findById(id).orElse(null);
    }

    public void AgregarSala(Sala sala) {
        salaRepository.save(sala);
    }

    public void EliminarSalaPorID(int id) {
        salaRepository.deleteById(id);
    }
}
