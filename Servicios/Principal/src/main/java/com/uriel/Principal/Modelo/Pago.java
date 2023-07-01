package com.uriel.Principal.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Pago {
    private Long id;
    private String descripcion;
    private double montoPago;
    private LocalDate fechaPago;
    private Cliente cliente;
    // Constructor, getters y setters
}