package com.cine_reserva_backend.model.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MetodoDePago {
    PAYPAL("Paypal"),
    TARJETA("Tarjeta"),
    EN_FISICO("En Fisico");
    private final String valor;

    public static MetodoDePago fromString(String valor) {
        for (MetodoDePago metodo : MetodoDePago.values()) {
            if (metodo.getValor().equalsIgnoreCase(valor)) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Método de pago no válido: " + valor);
    }
}
