package com.uriel.Principal.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DatosMedicos {
    private Long id;
    private int edad;
    private double peso;
    private double estatura;
    private String padecimientoMedico;
    private boolean consumoMedicamento;
    private String presionArterial;
    // Constructor, getters y setters
}