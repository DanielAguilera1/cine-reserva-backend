package com.cine_reserva_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TiqueteDTO {
    private String asientoPuesto;
    private Long usuarioId;
    private String funcionId;
    private Double precio;
    private String metodoDePago;
}
