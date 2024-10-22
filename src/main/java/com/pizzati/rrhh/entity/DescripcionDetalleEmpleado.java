package com.pizzati.rrhh.entity;

import com.pizzati.rrhh.utilities.QuincenaAsignada;
import com.pizzati.rrhh.utilities.TipoElemento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    QuincenaAsignada quincenaAsignada;
    TipoElemento tipoElemento;
    BigDecimal monto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFinal;

    boolean activo;
}
