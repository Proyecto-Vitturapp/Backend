package com.vittur.vitturapp.services;

import com.vittur.vitturapp.model.Vehiculo;
import com.vittur.vitturapp.repository.VehiculoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List getAllVehiculos(){
        return vehiculoRepository.findAll();
    }

    public void save(Vehiculo vehiculo){
        vehiculoRepository.save(vehiculo);
    }

    public Vehiculo getVehiculoById(String matricula){
        return vehiculoRepository.findById(matricula).get();
    }

    public void deleteVehiculo(String matricula){
        vehiculoRepository.deleteById(matricula);
    }
}
