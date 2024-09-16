package com.cine_reserva_backend.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(value = "funcion")
public class Funcion {
    @Id
    private String id;
    private Long peliculaId;
    private Integer salaId;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Asiento> asientos;
}
