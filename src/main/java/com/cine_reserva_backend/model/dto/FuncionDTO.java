package com.cine_reserva_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FuncionDTO {
    private String id;
    private Long peliculaId;
    private Integer salaId;
    private Date fechaInicio;
    private Date fechaFin;
}
