package com.cine_reserva_backend.web.controller;

import com.cine_reserva_backend.model.table.Tiquete;
import com.cine_reserva_backend.service.FuncionService;
import com.cine_reserva_backend.service.TiqueteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiquetes")
public class TiqueteController {
    private final TiqueteService tiqueteService;

    public TiqueteController(TiqueteService tiqueteService) {
        this.tiqueteService = tiqueteService;
    }

    @GetMapping
    public ResponseEntity<List<Tiquete>> ListaTiquetes() {
        return ResponseEntity.ok(tiqueteService.ListaTiquetes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tiquete> ObtenerTiquetePorID(@PathVariable long id) {
        Tiquete tiquete = tiqueteService.ObtenerTiquetePorID(id);
        if (tiquete == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tiquete);
    }
}
