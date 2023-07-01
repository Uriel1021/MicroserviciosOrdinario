package com.uriel.Principal.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ubicacion {
    private Long id;
    private String estado;
    private String colonia;
    private String codigoPostal;

    // Constructor, getters y setters
}
