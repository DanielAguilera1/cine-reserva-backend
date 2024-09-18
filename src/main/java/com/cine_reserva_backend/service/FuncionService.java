package com.cine_reserva_backend.service;

import com.cine_reserva_backend.model.document.Asiento;
import com.cine_reserva_backend.model.document.Funcion;
import com.cine_reserva_backend.model.dto.FuncionDTO;
import com.cine_reserva_backend.model.dto.TiqueteDTO;
import com.cine_reserva_backend.model.table.MetodoDePago;
import com.cine_reserva_backend.model.table.Tiquete;
import com.cine_reserva_backend.repository.FuncionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FuncionService {
    private final FuncionRepository funcionRepository;
    private final PeliculaService peliculaService;
    private final SalaService salaService;
    private final TiqueteService tiqueteService;

    public FuncionService(FuncionRepository funcionRepository, PeliculaService peliculaService, SalaService salaService, @Lazy TiqueteService tiqueteService) {
        this.funcionRepository = funcionRepository;
        this.peliculaService = peliculaService;
        this.salaService = salaService;
        this.tiqueteService = tiqueteService;
    }

    public List<FuncionDTO> ListaFunciones() {
        return funcionRepository.findAll().stream().map(this::crearFuncionDTO).toList();
    }

    public FuncionDTO ObtenerFuncionPorID(String id) {
        return funcionRepository.findById(id)
                .map(funcion -> new FuncionDTO(funcion.getId(),
                        peliculaService.ObtenerPeliculaPorID(funcion.getPeliculaId()).getTitulo(),
                        funcion.getSalaId(), funcion.getFechaInicio(), funcion.getFechaFin()))
                .orElse(null);
    }

    public void CrearFuncion(Funcion funcion) throws Exception {
        if (funcion.getId() != null)
            throw new Exception("No puedes proporcionar un ID para la funcion");

        if (peliculaService.ObtenerPeliculaPorID(funcion.getPeliculaId()) == null)
            throw new Exception("La Pelicula para esta funcion no existe");

        if (salaService.ObtenerSalaPorID(funcion.getSalaId()) == null)
            throw new Exception("La Sala para esta funcion no existe");

        if (funcionRepository.existsBySalaIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(funcion.getSalaId(),
                funcion.getFechaInicio(), funcion.getFechaFin())) {
            throw new Exception("Esta funcion ya tiene esta siendo usada en esas mismas fechas");
        }

        funcionRepository.save(new Funcion(null, funcion.getPeliculaId(), funcion.getSalaId(),
                funcion.getFechaInicio(), funcion.getFechaFin(), CrearAsientos()));
    }

    public List<FuncionDTO> obtenerFuncionesPorDia(Date dia) {
        return funcionRepository.findByFechaInicioBetween(inicioDelDia(dia), finDelDia(dia))
                .map(listaFunciones -> listaFunciones.stream()
                        .map(this::crearFuncionDTO).toList())
                .orElse(Collections.emptyList());
    }

    private Date inicioDelDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date finDelDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public List<Asiento> obtenerAsientosDeFuncionPorID(String id, Boolean disponible) {
        return funcionRepository.findById(id).map(
                        listaAsientos -> listaAsientos.getAsientos().stream()
                                .filter(asiento -> disponible == null || disponible.equals(asiento.getDisponible()))
                                .map(asiento -> new Asiento(asiento.getNumero(), asiento.getPrecio(), asiento.getDisponible()))
                                .toList())
                .orElse(null);
    }

    @Transactional
    public void reservarEntradas(TiqueteDTO tiqueteDTO) throws Exception {
        Funcion funcion = funcionRepository.findById(tiqueteDTO.getFuncionId())
                .orElseThrow(() -> new RuntimeException("la funcion no existe"));

        Asiento asientoReservado = funcion.getAsientos().stream()
                .filter(asiento -> asiento.getNumero().equals(tiqueteDTO.getAsientoPuesto()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El asiento no existe en esta función"));

        if (asientoReservado.getDisponible().equals(Boolean.FALSE))
            throw new Exception("El asiento ya está ocupado");
        if (!tiqueteDTO.getPrecio().equals(asientoReservado.getPrecio()))
            throw new Exception("El tiquete tiene un precio diferente precio al de la funcion");

        asientoReservado.setDisponible(false);

        tiqueteService.AgregarTiquete(new Tiquete(tiqueteDTO.getAsientoPuesto(), tiqueteDTO.getUsuarioId(),
                tiqueteDTO.getFuncionId(), tiqueteDTO.getPrecio(), LocalDateTime.now(),
                MetodoDePago.fromString(tiqueteDTO.getMetodoDePago())));

        funcionRepository.save(funcion);
    }

    private List<Asiento> CrearAsientos() {
        return Arrays.asList(
                // Fila A - $10,000 COP
                new Asiento("A1", 10000.0, true),
                new Asiento("A2", 10000.0, true),
                new Asiento("A3", 10000.0, true),
                new Asiento("A4", 10000.0, true),
                new Asiento("A5", 10000.0, true),

                // Fila B - $12,000 COP
                new Asiento("B1", 12000.0, true),
                new Asiento("B2", 12000.0, true),
                new Asiento("B3", 12000.0, true),
                new Asiento("B4", 12000.0, true),
                new Asiento("B5", 12000.0, true),

                // Fila C - $15,000 COP
                new Asiento("C1", 15000.0, true),
                new Asiento("C2", 15000.0, true),
                new Asiento("C3", 15000.0, true),
                new Asiento("C4", 15000.0, true),
                new Asiento("C5", 15000.0, true),

                // Fila D - $18,000 COP
                new Asiento("D1", 18000.0, true),
                new Asiento("D2", 18000.0, true),
                new Asiento("D3", 18000.0, true),
                new Asiento("D4", 18000.0, true),
                new Asiento("D5", 18000.0, true),

                // Fila E - $20,000 COP
                new Asiento("E1", 20000.0, true),
                new Asiento("E2", 20000.0, true),
                new Asiento("E3", 20000.0, true),
                new Asiento("E4", 20000.0, true),
                new Asiento("E5", 20000.0, true));
    }

    public FuncionDTO crearFuncionDTO(Funcion funcion) {
        return new FuncionDTO(funcion.getId(),
                peliculaService.ObtenerPeliculaPorID(funcion.getPeliculaId()).getTitulo(),
                funcion.getSalaId(), funcion.getFechaInicio(), funcion.getFechaFin());
    }

}
