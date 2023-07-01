package com.uriel.Modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "servicio")
@Setter
@Getter
@ToString
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "concepto_cobro")
    private String conceptoCobro;

    @Column(name = "monto_cobro")
    private double montoCobro;

    @Column(name = "descripcion_cobro")
    private String descripcionCobro;

    // Constructor, getters y setters
}