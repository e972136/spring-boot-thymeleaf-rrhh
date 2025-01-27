package com.pizzati.rrhh.entity;

import com.pizzati.rrhh.utilities.QuincenaAsignada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanillaHead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int sucursalId;

    @Enumerated(EnumType.STRING)
    QuincenaAsignada quincenaAsignada;

    LocalDate fechaInicio;
    LocalDate fechaFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String observacion;

    boolean activa;

}
