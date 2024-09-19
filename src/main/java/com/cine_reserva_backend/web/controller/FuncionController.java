package com.cine_reserva_backend.web.controller;

import com.cine_reserva_backend.model.document.Asiento;
import com.cine_reserva_backend.model.document.Funcion;
import com.cine_reserva_backend.model.dto.FechaRequest;
import com.cine_reserva_backend.model.dto.FuncionCrearDTO;
import com.cine_reserva_backend.model.dto.FuncionDTO;
import com.cine_reserva_backend.model.dto.TiqueteDTO;
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
    public ResponseEntity<FuncionDTO> listaFunciones(@PathVariable String id) {
        FuncionDTO funcionDTO = funcionService.ObtenerFuncionPorID(id);
        if (funcionDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(funcionService.ObtenerFuncionPorID(id));
    }

    @PostMapping
    public ResponseEntity<String> agregarFuncion(@RequestBody FuncionCrearDTO funcion) {
        try {
            Funcion funcionCreada = new Funcion(funcion.getPeliculaId(), funcion.getSalaId(), funcion.getFechaInicio(), funcion.getFechaFin());
            funcionService.CrearFuncion(funcionCreada);
            return ResponseEntity.ok(funcionService.crearFuncionDTO(funcionCreada).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/dia")
    public ResponseEntity<List<FuncionDTO>> obtenerFuncionesDelDia(@RequestBody FechaRequest fechaRequest) {
        Calendar cal = Calendar.getInstance();
        cal.set(fechaRequest.getYear(), fechaRequest.getMonth(), fechaRequest.getDay());

        return ResponseEntity.ok(funcionService.obtenerFuncionesPorDia(cal.getTime()));
    }

    @GetMapping("/dia/hoy")
    public ResponseEntity<List<FuncionDTO>> obtenerFuncionesDeHoy() {
        return ResponseEntity.ok(funcionService.obtenerFuncionesPorDia(new Date()));
    }

    @GetMapping("/asientos/{id}")
    public ResponseEntity<List<Asiento>> obtenerAsientosDeFuncionPorID(@PathVariable String id, @RequestParam(required = false) Boolean disponible) {
        List<Asiento> listaDeAsientos = funcionService.obtenerAsientosDeFuncionPorID(id, disponible);
        if (listaDeAsientos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(listaDeAsientos);
    }

    @PostMapping("/reservar")
    public ResponseEntity<String> reservarEntradas(@RequestBody TiqueteDTO tiqueteDTO) {
        try {
            funcionService.reservarEntradas(tiqueteDTO);
            return ResponseEntity.ok("Asiento reservado con exito!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
