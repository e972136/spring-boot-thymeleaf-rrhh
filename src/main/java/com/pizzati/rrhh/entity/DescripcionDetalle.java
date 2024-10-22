package com.pizzati.rrhh.entity;

import com.pizzati.rrhh.utilities.QuincenaAsignada;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    QuincenaAsignada quincenaAsignada;
    boolean activo;
}
