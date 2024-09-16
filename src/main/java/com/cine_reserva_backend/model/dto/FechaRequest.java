package com.cine_reserva_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FechaRequest {
    private int year;
    private int month;
    private int day;
}
