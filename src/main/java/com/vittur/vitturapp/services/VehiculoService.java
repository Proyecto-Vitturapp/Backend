package com.vittur.vitturapp.services;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.model.Vehiculo;
import com.vittur.vitturapp.repository.UsuarioRepository;
import com.vittur.vitturapp.repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Vehiculo> getAllVehiculos(){
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

    public List<Vehiculo> findByIdUsuario(Integer idUsuario){
        return vehiculoRepository.findByUsuariosIdUsuario(idUsuario);
    }

    @Transactional
    public void asociarVehiculo(Integer userId, String plate){
        Usuario usuario = usuarioRepository.findByIdUsuario(userId);
        Vehiculo vehicle = vehiculoRepository.findByMatricula(plate);
        if (usuario != null && vehicle != null) {
            if (!usuario.getVehiculos().contains(vehicle)) {
                usuario.getVehiculos().add(vehicle);
                vehicle.getUsuarios().add(usuario);
                vehiculoRepository.save(vehicle);
            }
        }
    }
}
