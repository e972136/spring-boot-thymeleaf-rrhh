package com.pizzati.rrhh.dto;

import com.pizzati.rrhh.entity.Empleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class EmpleadoDto {
    int id;

    String identidad;
    String nombreCompleto;
    int sucursalId;
    Integer departamentoId;
    Integer puestoId;
    BigDecimal sueldoMensual;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaContratacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaUltimoPago;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaDespido;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaUltimasVacaciones;
    String obs;

    BigDecimal deducciones;
    BigDecimal ingresos;

    BigDecimal sueldoFinal;
}
