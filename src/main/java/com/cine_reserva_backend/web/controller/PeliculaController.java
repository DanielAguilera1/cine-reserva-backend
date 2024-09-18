package com.cine_reserva_backend.web.controller;

import com.cine_reserva_backend.model.table.Pelicula;
import com.cine_reserva_backend.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> ObtenerPeliculas() {
        return ResponseEntity.ok(this.peliculaService.ListaPeliculas());
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Pelicula>> ObtenerPeliculasPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(this.peliculaService.ObtenerPeliculasPorGenero(genero));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> ObtenerPeliculaPorID(@PathVariable long id) {
        Pelicula pelicula = this.peliculaService.ObtenerPeliculaPorID(id);
        if (pelicula == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping()
    public ResponseEntity<String> AgregarPelicula(@RequestBody Pelicula pelicula) {
        try {
            this.peliculaService.AgregarPelicula(pelicula);
            return ResponseEntity.ok("Pelicula Agregada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<String> ModificarPelicula(@RequestBody Pelicula pelicula) {
        try {
            this.peliculaService.EditarPelicula(pelicula);
            return ResponseEntity.ok("Pelicula Modificada Con Exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> EliminarPelicula(@PathVariable Long id) {
        if (id != null) {
            this.peliculaService.EliminarPeliculaPorID(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

}
