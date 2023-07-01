package com.uriel.Modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "datos_medicos")
public class DatosMedicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int edad;
    private double peso;
    private double estatura;

    @Column(name = "padecimiento_medico")
    private String padecimientoMedico;

    @Column(name = "consumo_medicamento")
    private boolean consumoMedicamento;

    @Column(name = "presion_arterial")
    private String presionArterial;

    @OneToOne(mappedBy = "datosMedicos")
    private Cliente cliente;

    // Constructor, getters y setters
}