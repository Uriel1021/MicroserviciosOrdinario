package com.uriel.Modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "datos_contacto")
public class DatosContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telefono;
    private String correo;

    @OneToOne(mappedBy = "datosContacto")
    private Cliente cliente;

    // Constructor, getters y setters
}
