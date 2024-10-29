package com.pizzati.rrhh.entity;

import com.pizzati.rrhh.utilities.QuincenaAsignada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescripcionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    String descripcion;
    BigDecimal monto;

    boolean obligatoria;

    @Enumerated(EnumType.STRING)
    QuincenaAsignada quincenaAsignada;

    boolean activo;
}
