package com.cine_reserva_backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine_reserva_backend.model.dto.TiqueteDTO;
import com.cine_reserva_backend.model.table.MetodoDePago;
import com.cine_reserva_backend.model.table.Tiquete;
import com.cine_reserva_backend.service.FuncionService;
import com.cine_reserva_backend.service.TiqueteService;

@RestController
@RequestMapping("/api/v1/tiquetes")
public class TiqueteController {
    private final TiqueteService tiqueteService;

    public TiqueteController(TiqueteService tiqueteService, FuncionService funcionService) {
        this.tiqueteService = tiqueteService;
    }

    @GetMapping
    public ResponseEntity<List<Tiquete>> ListaTiquetes() {
        return ResponseEntity.ok(tiqueteService.ListaTiquetes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tiquete> ObtenerTiquetePorID(@PathVariable long id) {
        return ResponseEntity.ok(tiqueteService.ObtenerTiquetePorID(id));
    }

    @PostMapping()
    public ResponseEntity<String> AgregarTiquete(@RequestBody TiqueteDTO tiqueteDTO) {
        try {
            tiqueteService.AgregarTiquete(new Tiquete(
                    tiqueteDTO.getAsientoPuesto(), tiqueteDTO.getUsuarioId(),
                    tiqueteDTO.getFuncionId(), tiqueteDTO.getPrecio(),
                    LocalDateTime.now(), MetodoDePago.fromString(tiqueteDTO.getMetodoDePago())));
            return ResponseEntity.ok("Tiquete creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
