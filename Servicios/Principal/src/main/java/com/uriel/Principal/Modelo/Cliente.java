package com.uriel.Principal.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Cliente {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private DatosMedicos datosMedicos;
    private Ubicacion ubicacion;
    private DatosContacto datosContacto;
}