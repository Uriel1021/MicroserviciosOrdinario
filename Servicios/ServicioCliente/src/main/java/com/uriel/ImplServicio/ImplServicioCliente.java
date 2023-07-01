package com.uriel.ImplServicio;

import com.uriel.Modelo.Cliente;
import com.uriel.Modelo.Pago;
import com.uriel.Modelo.Suscripcion;
import com.uriel.Repositorio.ClienteRepositorio;
import com.uriel.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImplServicioCliente implements ClienteServicio {
    private final ClienteRepositorio clienteRepositorio;

    public ImplServicioCliente(@Autowired ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Integer GuardarCliente(Cliente cliente) {
     Cliente cliente1 = clienteRepositorio.save(cliente);
     if (!cliente1.equals(null)){
         return 1;
     }else{
         return 0;
     }
    }

    @Override
    public List<Cliente> ObtenerClientes() {
        return (List<Cliente>) clienteRepositorio.findAll();
    }

    @Override
    public void EliminarCliente(Integer id) {
        clienteRepositorio.deleteAllById(Collections.singleton(Long.valueOf(id)));
    }


    @Override
    public List<Cliente> ObtenerClientePorNombre(String nombre, String apellidoP, String apellidoM) {
        return clienteRepositorio.findByNombreOrApellidoPaternoOrApellidoMaterno(nombre,apellidoP,apellidoM);
    }

    @Override
    public List<Pago> ObtenerPagosCliente(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepositorio.findById(Long.valueOf(id));
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return cliente.getPagos();
        } else {
            return null;
        }
    }

    @Override
    public List<Suscripcion> ObtenerSuscripcionesCliente(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepositorio.findById(Long.valueOf(id));
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return cliente.getSuscripciones();
        } else {
            return null;
        }
    }

    @Override
    public Optional<Cliente> ObtenerClientePorID(Integer id) {
        return clienteRepositorio.findById(Long.valueOf(id));
    }
}
