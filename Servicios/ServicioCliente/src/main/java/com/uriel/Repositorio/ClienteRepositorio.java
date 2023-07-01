package com.uriel.Repositorio;

import com.uriel.Modelo.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {
    List<Cliente> findByNombreOrApellidoPaternoOrApellidoMaterno(String nombre, String apellidoPaterno, String apellidoMaterno);
}
