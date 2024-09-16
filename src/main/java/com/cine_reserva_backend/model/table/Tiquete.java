package com.cine_reserva_backend.model.table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Tiquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asiento_puesto", length = 50, nullable = false)
    @NonNull()
    private String asientoPuesto;

    @Column(name = "usuario_id", nullable = false)
    @NonNull()
    private Long usuarioId;

    @Column(name = "funcion_id", nullable = false)
    @NonNull()
    private String funcionId;

    @Column(columnDefinition = "Decimal(5,2)", nullable = false)
    @NonNull()
    private Double precio;

    @Column(name = "fecha_compra", columnDefinition = "DATETIME", nullable = false)
    @NonNull()
    private LocalDateTime fechaCompra;

    @Column(name = "metodo_pago", length = 20, nullable = false)
    @NonNull()
    private MetodoDePago metodoDePago;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;
}
