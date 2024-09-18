package com.cine_reserva_backend.web.controller;

import com.cine_reserva_backend.model.table.Sala;
import com.cine_reserva_backend.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {
    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public ResponseEntity<List<Sala>> ListaSalas() {
        return ResponseEntity.ok(salaService.ListaSalas());
    }

    @PostMapping
    public ResponseEntity<Void> AgregarSala(@RequestBody Sala sala) {
        if (sala.getId() != null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        this.salaService.AgregarSala(sala);
        return ResponseEntity.ok().build();
    }
}
