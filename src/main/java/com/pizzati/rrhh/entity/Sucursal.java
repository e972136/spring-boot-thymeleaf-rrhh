package com.pizzati.rrhh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nombreSucursal;

    String direccion;
}
