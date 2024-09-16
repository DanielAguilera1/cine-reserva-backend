package com.cine_reserva_backend.controller;

import com.cine_reserva_backend.model.document.Funcion;
import com.cine_reserva_backend.model.dto.FechaRequest;
import com.cine_reserva_backend.service.FuncionService;
import com.cine_reserva_backend.service.PeliculaService;
import com.cine_reserva_backend.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funciones")
public class FuncionController {

    private final FuncionService funcionService;
    private final PeliculaService peliculaService;
    private final SalaService salaService;

    public FuncionController(SalaService salaService, PeliculaService peliculaService, FuncionService funcionService) {
        this.salaService = salaService;
        this.peliculaService = peliculaService;
        this.funcionService = funcionService;
    }

    @GetMapping
    public ResponseEntity<List<Funcion>> ListaFunciones() {
        return ResponseEntity.ok(funcionService.ListaFunciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcion> ListaFunciones(@PathVariable String id) {
        return ResponseEntity.ok(funcionService.ObtenerFuncionPorID(id));
    }

    @PostMapping
    public ResponseEntity<String> AgregarFuncion(@RequestBody Funcion funcion) throws Exception {
        try {
            funcionService.CrearFuncion(funcion);
            return ResponseEntity.ok("Funcion Creada Con Exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/dia")
    public ResponseEntity<List<Funcion>> obtenerFuncionesDelDia(@RequestBody FechaRequest fechaRequest) {
        return ResponseEntity.ok(funcionService.obtenerFuncionesPorDia(
                new Date(fechaRequest.getYear(), fechaRequest.getMonth(), fechaRequest.getDay())));
    }

    @GetMapping("/dia/hoy")
    public ResponseEntity<List<Funcion>> obtenerFuncionesDeHoy() {
        return ResponseEntity.ok(funcionService.obtenerFuncionesPorDia(new Date()));
    }
}
