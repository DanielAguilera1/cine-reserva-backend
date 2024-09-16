package com.cine_reserva_backend.controller;

import com.cine_reserva_backend.model.document.Funcion;
import com.cine_reserva_backend.model.dto.FechaRequest;
import com.cine_reserva_backend.model.dto.FuncionCrearDTO;
import com.cine_reserva_backend.model.dto.FuncionDTO;
import com.cine_reserva_backend.service.FuncionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funciones")
public class FuncionController {

    private final FuncionService funcionService;

    public FuncionController(FuncionService funcionService) {
        this.funcionService = funcionService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionDTO>> listaFunciones() {
        return ResponseEntity.ok(funcionService.ListaFunciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcion> listaFunciones(@PathVariable String id) {
        return ResponseEntity.ok(funcionService.ObtenerFuncionPorID(id));
    }

    @PostMapping
    public ResponseEntity<String> agregarFuncion(@RequestBody FuncionCrearDTO funcion) throws Exception {
        try {
            funcionService.CrearFuncion(new Funcion(
                    funcion.getPeliculaId(), funcion.getSalaId(),
                    funcion.getFechaInicio(), funcion.getFechaFin()));
            return ResponseEntity.ok("Funcion Creada Con Exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/dia")
    public ResponseEntity<List<FuncionDTO>> obtenerFuncionesDelDia(@RequestBody FechaRequest fechaRequest) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, fechaRequest.getYear());
        cal.set(Calendar.MONTH, fechaRequest.getMonth());
        cal.set(Calendar.DATE, fechaRequest.getDay());

        return ResponseEntity.ok(funcionService.obtenerFuncionesPorDia(cal.getTime()));
    }

    @GetMapping("/dia/hoy")
    public ResponseEntity<List<FuncionDTO>> obtenerFuncionesDeHoy() {
        return ResponseEntity.ok(funcionService.obtenerFuncionesPorDia(new Date()));
    }
}
