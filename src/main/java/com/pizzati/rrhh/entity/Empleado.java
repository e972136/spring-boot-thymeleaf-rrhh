package com.pizzati.rrhh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String identidad;
    String nombreCompleto;
    int sucursalId;
    int departamentoId;
    int puestoId;
    BigDecimal sueldoMensual;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaContratacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ColumnDefault("'1900/01/01'")
    LocalDate fechaUltimoPago;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ColumnDefault("'1900/01/01'")
    LocalDate fechaDespido;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ColumnDefault("'1900/01/01'")
    LocalDate fechaUltimasVacaciones;
    String obs;

    @ColumnDefault("1")
    boolean activo;
}
