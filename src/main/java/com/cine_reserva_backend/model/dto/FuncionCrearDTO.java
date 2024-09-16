package com.cine_reserva_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FuncionCrearDTO {
    private Long peliculaId;

    private Integer salaId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date fechaFin;
}
