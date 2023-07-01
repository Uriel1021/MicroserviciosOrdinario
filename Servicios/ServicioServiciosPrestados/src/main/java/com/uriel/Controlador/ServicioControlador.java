package com.uriel.Controlador;

import com.uriel.ImplServicio.ServicioServicioImpl;
import com.uriel.Modelo.Servicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
public class ServicioControlador {
    private final ServicioServicioImpl servicioImpl;

    public ServicioControlador(ServicioServicioImpl servicioImpl) {
        this.servicioImpl = servicioImpl;
    }


    @PostMapping("/crear")
    public String crearServicio(@RequestBody Servicio servicio) {
        int respuesta = servicioImpl.GuardarServicio(servicio);
        if (respuesta == 0){
            return "No se guardo";
        }else{
            return "Se guardo correctamente";
        }
    }

    @GetMapping("listar")
    public List<Servicio> obtenerServicios() {
        return servicioImpl.ObtenerServicios();
    }

    @GetMapping("/buscarporid")
    public Optional<Servicio> obtenerServicioPorId(@RequestParam(required = true) Integer id) {
        return servicioImpl.ObtenerServicioPorId(id);
    }

    @PostMapping("/actualizar")
    public String actualizarServicio(@RequestBody Servicio servicio) {
        int respuesta = servicioImpl.ActualizarServicio(servicio);
        if (respuesta == 0){
            return "No se actualizo";
        }else{
            return "Se actualizo correctamente";
        }
    }

    @PostMapping("/eliminar")
    public String eliminarServicio(@RequestParam(required = true) Integer id) {
        servicioImpl.EliminarServicio(id);
        return "Se elimino correctamente";
    }
}
