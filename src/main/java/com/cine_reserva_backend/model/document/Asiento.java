package com.cine_reserva_backend.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Asiento {
    private String numero;
    private Double precio;
    private Boolean disponible;
}
