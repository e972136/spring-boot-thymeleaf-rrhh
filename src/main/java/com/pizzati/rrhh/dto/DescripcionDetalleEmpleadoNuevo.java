package com.pizzati.rrhh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescripcionDetalleEmpleadoNuevo {

    int id;

    int empleadoId;
    String nombreEmpleado;
    String sueldoMensual;
    Integer descripcionDetalleId;

    Integer quincenaAsignada;
    Integer tipoElemento;
    BigDecimal monto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFinal;

    public DescripcionDetalleEmpleadoNuevo(int id, int idEmpleado, String nombreEmpleado, String sueldoMensual) {
        this.id = id;
        this.empleadoId = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.sueldoMensual = sueldoMensual;
        this.monto = BigDecimal.ZERO;
    }
}
