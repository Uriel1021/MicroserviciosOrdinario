package com.uriel.Servicio;

import com.uriel.Modelo.Cliente;
import com.uriel.Modelo.Pago;
import com.uriel.Modelo.Suscripcion;

import java.util.List;
import java.util.Optional;

public interface ClienteServicio {
    Integer GuardarCliente(Cliente cliente);
    List<Cliente> ObtenerClientes();

    void EliminarCliente(Integer id);
    List<Cliente> ObtenerClientePorNombre(String nombre, String apellidoP,String apellidoM);
    List<Pago> ObtenerPagosCliente(Integer id);
    List<Suscripcion> ObtenerSuscripcionesCliente(Integer id);
    Optional<Cliente> ObtenerClientePorID(Integer id);
}
