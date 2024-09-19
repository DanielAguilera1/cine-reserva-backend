package com.cine_reserva_backend.service;

import com.cine_reserva_backend.model.table.Pelicula;
import com.cine_reserva_backend.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> ListaPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula ObtenerPeliculaPorID(long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public void AgregarPelicula(Pelicula pelicula) throws Exception {
        if (pelicula.getId() != null) throw new Exception("Para crear una pelicula no debe tener ID");
        peliculaRepository.save(pelicula);
    }

    public void EditarPelicula(Pelicula pelicula) throws Exception {
        if (peliculaRepository.findById(pelicula.getId()).isPresent()) {
            peliculaRepository.save(pelicula);
        } else {
            throw new Exception("Pelicula No Encontrada");
        }
    }

    public void EliminarPeliculaPorID(long id) {
        peliculaRepository.deleteById(id);
    }

    public List<Pelicula> ObtenerPeliculasPorGenero(String genero) {
        return peliculaRepository.findAllByGeneroIgnoreCase(genero);
    }
}
