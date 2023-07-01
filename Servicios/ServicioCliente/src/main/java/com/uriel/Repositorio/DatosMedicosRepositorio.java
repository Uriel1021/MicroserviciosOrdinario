package com.uriel.Repositorio;

import com.uriel.Modelo.DatosMedicos;
import org.springframework.data.repository.CrudRepository;

public interface DatosMedicosRepositorio extends CrudRepository<DatosMedicos, Long> {
}