package com.pizzati.rrhh.entity;

import com.pizzati.rrhh.utilities.QuincenaAsignada;
import com.pizzati.rrhh.utilities.TipoElemento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescripcionDetalleEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int empleadoId;
    int descripcionDetalleId;

    @Enumerated(EnumType.STRING)
    QuincenaAsignada quincenaAsignada;

    @Enumerated(EnumType.STRING)
    TipoElemento tipoElemento;

    BigDecimal monto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFinal;

    boolean activo;
}
