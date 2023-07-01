package com.uriel.Principal.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Servicio {
    private Integer id;
    private String conceptoCobro;
    private double montoCobro;
    private String descripcionCobro;

    // Constructor, getters y setters
}