package com.uriel.ImplServicio;

import com.uriel.Modelo.Servicio;
import com.uriel.Repositorio.ServicioRepositorio;
import com.uriel.Servicio.ServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicioImpl implements ServicioServicio {
    private final ServicioRepositorio servicioRepositorio;

    public ServicioServicioImpl(@Autowired ServicioRepositorio servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

    @Override
    public Integer GuardarServicio(Servicio servicio) {
        Servicio servicio1 = servicioRepositorio.save(servicio);
        if (!servicio1.equals(null)){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public List<Servicio> ObtenerServicios() {
        return (List<Servicio>) servicioRepositorio.findAll();
    }

    @Override
    public Optional<Servicio> ObtenerServicioPorId(Integer id) {
        return servicioRepositorio.findById(id);
    }

    @Override
    public Integer ActualizarServicio(Servicio servicio) {
        Servicio servicio1 = servicioRepositorio.save(servicio);
        if (!servicio1.equals(null)){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void EliminarServicio(Integer id) {
        servicioRepositorio.deleteById(id);
    }
}
