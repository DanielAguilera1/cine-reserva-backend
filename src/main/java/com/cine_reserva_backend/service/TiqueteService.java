package com.cine_reserva_backend.service;

import com.cine_reserva_backend.model.table.Tiquete;
import com.cine_reserva_backend.repository.TiqueteRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiqueteService {

    private final TiqueteRepository tiqueteRepository;
    private final FuncionService funcionService;
    private final UsuarioService usuarioService;

    public TiqueteService(TiqueteRepository tiqueteRepository, @Lazy FuncionService funcionService, @Lazy UsuarioService usuarioService) {
        this.tiqueteRepository = tiqueteRepository;
        this.funcionService = funcionService;
        this.usuarioService = usuarioService;
    }

    public List<Tiquete> ListaTiquetes() {
        return tiqueteRepository.findAll();
    }

    public Tiquete ObtenerTiquetePorID(long id) {
        return tiqueteRepository.findById(id).orElse(null);
    }

    public void AgregarTiquete(Tiquete tiquete) {
        if (tiquete.getId() != null) throw new RuntimeException("No puedes colocar ID al tiquete");
        if (usuarioService.obtenerUsuarioPorID(tiquete.getUsuarioId()) == null)
            throw new RuntimeException("El Usuario no existe");
        if (funcionService.ObtenerFuncionPorID(tiquete.getFuncionId()) == null)
            throw new RuntimeException("La Pelicula para esta funcion no existe");

        tiqueteRepository.save(tiquete);
    }

    public void EliminarTiquetePorID(long id) {
        tiqueteRepository.deleteById(id);
    }
}
