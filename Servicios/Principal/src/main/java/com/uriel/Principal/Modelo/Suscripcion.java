package com.uriel.Principal.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Suscripcion {

    private Long id;
    private String tipoSuscripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaCorte;
    private Cliente cliente;

    // Constructor, getters y setters
}