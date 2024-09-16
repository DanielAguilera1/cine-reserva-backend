package com.cine_reserva_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine_reserva_backend.model.table.Sala;
import com.cine_reserva_backend.service.SalaService;

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
