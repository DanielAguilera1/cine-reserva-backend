package com.cine_reserva_backend.service;

import com.cine_reserva_backend.model.document.Funcion;
import com.cine_reserva_backend.repository.FuncionRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FuncionService {
    private final FuncionRepository funcionRepository;
    private final PeliculaService peliculaService;
    private final SalaService salaService;

    FuncionService(FuncionRepository funcionRepository, PeliculaService peliculaService, SalaService salaService) {
        this.funcionRepository = funcionRepository;
        this.peliculaService = peliculaService;
        this.salaService = salaService;
    }

    public List<Funcion> ListaFunciones() {
        return funcionRepository.findAll();
    }

    public Funcion ObtenerFuncionPorID(String id) {
        return funcionRepository.findById(id).orElse(null);
    }

    public void CrearFuncion(Funcion funcion) throws Exception {
        if (peliculaService.ObtenerPeliculaPorID(funcion.getPeliculaId()).getId() == null) throw new Exception("La Pelicula para esta funcion no existe");
        if (salaService.ObtenerSalaPorID(funcion.getSalaId()).getId() == null) throw new Exception("La Sala para esta funcion no existe");

        if (funcionRepository.existsBySalaIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual
                (funcion.getSalaId(), funcion.getFechaInicio(), funcion.getFechaFin())) {
            throw new Exception("Conflicto en las fechas de inicio y fin");
        }

        funcionRepository.save(funcion);
    }

    public List<Funcion> obtenerFuncionesPorDia(Date dia) {
        return funcionRepository.findByFechaInicioBetween(inicioDelDia(dia), finDelDia(dia)).orElse(null);
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
}
