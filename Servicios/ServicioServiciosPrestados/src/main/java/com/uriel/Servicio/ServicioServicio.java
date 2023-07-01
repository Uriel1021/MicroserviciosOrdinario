package com.uriel.Servicio;

import com.uriel.Modelo.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioServicio {
    Integer GuardarServicio(Servicio servicio);
    List<Servicio> ObtenerServicios();
    Optional<Servicio> ObtenerServicioPorId(Integer id);
    Integer ActualizarServicio(Servicio servicio);
    void EliminarServicio(Integer id);
}
