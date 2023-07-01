package com.uriel.Controlador;

import com.uriel.ImplServicio.ImplServicioCliente;
import com.uriel.Modelo.Cliente;
import com.uriel.Modelo.Pago;
import com.uriel.Modelo.Suscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {
    private final ImplServicioCliente implServicioCliente;

    public ClienteControlador(@Autowired ImplServicioCliente implServicioCliente) {
        this.implServicioCliente = implServicioCliente;
    }

    @PostMapping("/add")
    public String crearCliente(@RequestBody Cliente cliente) {
        Integer respuesta = implServicioCliente.GuardarCliente(cliente);
        if(respuesta == 0){
            return "No se guardo";
        }else {
            return "Se guardo correctamente";
        }
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long clienteId){
        implServicioCliente.EliminarCliente(Math.toIntExact(clienteId));
        return "Se elimino correctamente";
    }

    @GetMapping("/obtenerclienteporid")
    public Optional<Cliente> obtenerClientePorID(@RequestParam Long clienteId) {
        return implServicioCliente.ObtenerClientePorID(Math.toIntExact(clienteId));
    }


    @GetMapping("/list")
    List<Cliente> ListarClientes(){
        return implServicioCliente.ObtenerClientes();
    }

    @GetMapping("/buscar")
    public List<Cliente> buscarClientePorNombreYApellidos(@RequestParam(required = false) String nombre,
                                                          @RequestParam(required = false) String apellidoPaterno,
                                                          @RequestParam(required = false) String apellidoMaterno) {
        return implServicioCliente.ObtenerClientePorNombre(nombre,apellidoPaterno,apellidoMaterno);
    }

    @GetMapping("/listarpagos")
    public List<Pago> obtenerPagosCliente(@RequestParam Long clienteId) {
        return implServicioCliente.ObtenerPagosCliente(Math.toIntExact(clienteId));
    }

    @GetMapping("/listarsuscripciones")
    public List<Suscripcion> obtenerSuscripcionesCliente(@RequestParam Long clienteId) {
        return implServicioCliente.ObtenerSuscripcionesCliente(Math.toIntExact(clienteId));
    }

    @PostMapping("/agregarpagos")
    public String agregarPagoCliente(@RequestParam Long clienteId, @RequestBody Pago pago) {
        Optional<Cliente> clienteOptional = implServicioCliente.ObtenerClientePorID(Math.toIntExact(clienteId));
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            pago.setCliente(cliente);
            cliente.getPagos().add(pago);
            implServicioCliente.GuardarCliente(cliente);
            return "Se inserto correctamente el pago";
        } else {
            return "No se inserto el pago";
        }
    }


    @PostMapping("/agregarsuscripciones")
    public String agregarSuscripcionCliente(@RequestParam Long clienteId, @RequestBody Suscripcion suscripcion) {
        Optional<Cliente> clienteOptional = implServicioCliente.ObtenerClientePorID(Math.toIntExact(clienteId));
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            suscripcion.setCliente(cliente);
            cliente.getSuscripciones().add(suscripcion);
            implServicioCliente.GuardarCliente(cliente);
            return "Se agrego correctamente la suscripcion";
        } else {
            return "No se agrego suscripcion";
        }
    }
}