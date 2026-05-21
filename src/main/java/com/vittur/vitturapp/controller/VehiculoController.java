package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.VehiculoDTO;
import com.vittur.vitturapp.dtos.create.VehiculoCreateDTO;
import com.vittur.vitturapp.model.Vehiculo;
import com.vittur.vitturapp.services.RevisionService;
import com.vittur.vitturapp.services.UsuarioService;
import com.vittur.vitturapp.services.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehiculoController {
    @Autowired
    private RevisionService revisionService;
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/vehicles")
    public List<VehiculoDTO> getAllVehiculos(){
        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
        for (Vehiculo veh : vehiculos) {
            vehiculosDTO.add(toVehiculoDTO(veh));
        }
        return vehiculosDTO;
    }

    @GetMapping("/vehicles/{plate}")
    public VehiculoDTO getVehiculoById(@PathVariable String plate){
        Vehiculo vehiculo = vehiculoService.getVehiculoById(plate);
        return toVehiculoDTO(vehiculo);
    }

    @PostMapping("/vehicles")
    public ResponseEntity<?> addVehiculo(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO){
        try {
            Vehiculo vehiculo = new Vehiculo();
            setVehiculo(vehiculoCreateDTO, vehiculo);
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/vehicles/{plate}")
    public ResponseEntity<?> updateVehiculo(@PathVariable String plate, @Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO){
        try {
            Vehiculo currentVehiculo = vehiculoService.getVehiculoById(plate);
            setVehiculo(vehiculoCreateDTO, currentVehiculo);
            return new ResponseEntity<>(currentVehiculo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vehicles/{plate}")
    public ResponseEntity<?> deleteVehiculo(@PathVariable String plate){
        try {
            vehiculoService.deleteVehiculo(plate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public VehiculoDTO toVehiculoDTO(Vehiculo vehiculo){
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setMatricula(vehiculo.getMatricula());
        vehiculoDTO.setMarca(vehiculo.getMarca());
        vehiculoDTO.setModelo(vehiculo.getModelo());
        vehiculoDTO.setAnyoFabricacion(vehiculo.getAnyoFabricacion());
        vehiculoDTO.setTipoVehiculo(vehiculo.getTipoVehiculo());
        return vehiculoDTO;
    }

    private void setVehiculo(@RequestBody @Valid VehiculoCreateDTO vehiculoCreateDTO, Vehiculo vehiculo){
        vehiculo.setMatricula(vehiculoCreateDTO.getMatricula());
        vehiculo.setMarca(vehiculoCreateDTO.getMarca());
        vehiculo.setModelo(vehiculoCreateDTO.getModelo());
        vehiculo.setAnyoFabricacion(vehiculoCreateDTO.getAnyoFabricacion());
        vehiculo.setTipoVehiculo(vehiculoCreateDTO.getTipoVehiculo());
        vehiculoService.save(vehiculo);
    }
}

/*
Endpoints para front de vehiculo:
-GET todos los vehiculos con todos los datos
-GET por id mismo requisito
-Todos los datos de los Vehiculos de un usuario
-
*/