package com.cine_reserva_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine_reserva_backend.model.table.Pelicula;
import com.cine_reserva_backend.service.PeliculaService;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    PeliculaController(PeliculaService peliculaService) {
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
        if (pelicula != null)
            return ResponseEntity.ok(pelicula);
        return ResponseEntity.notFound().build();
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
