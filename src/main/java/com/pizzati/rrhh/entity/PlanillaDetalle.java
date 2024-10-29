package com.pizzati.rrhh.entity;

import com.pizzati.rrhh.utilities.TipoElemento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanillaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int planillaId;

    int empleadoId;

    @Enumerated(EnumType.STRING)
    TipoElemento tipoElemento;

    int decripcionDetalleId;

    BigDecimal cantidad;

    BigDecimal montoUnitario;

    BigDecimal montoTotal;
}
