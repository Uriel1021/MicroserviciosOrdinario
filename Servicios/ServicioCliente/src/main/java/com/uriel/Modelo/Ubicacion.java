package com.uriel.Modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ubicaciones")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String colonia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @OneToOne(mappedBy = "ubicacion")
    private Cliente cliente;

    // Constructor, getters y setters
}
