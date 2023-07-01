package com.uriel.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "datos_medicos_id", referencedColumnName = "id")
    @JsonIgnoreProperties("cliente")
    private DatosMedicos datosMedicos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    @JsonIgnoreProperties("cliente")
    private Ubicacion ubicacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "datos_contacto_id", referencedColumnName = "id")
    @JsonIgnoreProperties("cliente")
    private DatosContacto datosContacto;

    // Relación con pagos
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    // Relación con suscripciones
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Suscripcion> suscripciones;

    // Constructor, getters y setters
    public void setDatosMedicos(DatosMedicos datosMedicos) {
        this.datosMedicos = datosMedicos;
        datosMedicos.setCliente(this);
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        ubicacion.setCliente(this);
    }

    public void setDatosContacto(DatosContacto datosContacto) {
        this.datosContacto = datosContacto;
        datosContacto.setCliente(this);
    }
}