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
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Document(value = "funcion")
public class Funcion {
    @Id()
    private String id;

    @NonNull
    private Long peliculaId;

    @NonNull
    private Integer salaId;

    @NonNull
    private Date fechaInicio;

    @NonNull
    private Date fechaFin;
    private List<Asiento> asientos;
}
