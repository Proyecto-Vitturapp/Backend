package com.vittur.vitturapp.repository;

import com.vittur.vitturapp.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    List<Vehiculo> findByUsuariosIdUsuario(Integer idUsuario);

    Vehiculo findByMatricula(String matricula);

    long countByStatus(Integer status);

    List<Vehiculo> findByStatus(Integer status);
}
