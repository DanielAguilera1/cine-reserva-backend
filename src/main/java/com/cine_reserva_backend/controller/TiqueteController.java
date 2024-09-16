package com.cine_reserva_backend.controller;

import com.cine_reserva_backend.model.dto.TiqueteDTO;
import com.cine_reserva_backend.model.table.MetodoDePago;
import com.cine_reserva_backend.model.table.Tiquete;
import com.cine_reserva_backend.service.FuncionService;
import com.cine_reserva_backend.service.TiqueteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
