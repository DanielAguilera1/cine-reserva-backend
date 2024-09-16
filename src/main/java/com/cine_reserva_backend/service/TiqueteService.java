package com.cine_reserva_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cine_reserva_backend.model.table.Tiquete;
import com.cine_reserva_backend.repository.TiqueteRepository;

@Service
public class TiqueteService {

    private final TiqueteRepository tiqueteRepository;
    private final FuncionService funcionService;
    private final UsuarioService usuarioService;

    public TiqueteService(TiqueteRepository tiqueteRepository, FuncionService funcionService,
            UsuarioService usuarioService) {
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

    public void AgregarTiquete(Tiquete tiquete) throws Exception {
        if (tiquete.getId() != null)
            throw new Exception("No puedes colocar ID al tiquete");
        if (usuarioService.ObtenerFuncionPorID(tiquete.getUsuarioId()).getId().describeConstable().isEmpty())
            throw new Exception("El Usuario no existe");
        if (funcionService.ObtenerFuncionPorID(tiquete.getFuncionId()).getId().isEmpty())
            throw new Exception("La Pelicula para esta funcion no existe");

        tiqueteRepository.save(tiquete);
    }

    public void EliminarTiquetePorID(long id) {
        tiqueteRepository.deleteById(id);
    }
}
