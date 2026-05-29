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
@RequestMapping("/api")
public class VehiculoController {
    @Autowired
    private RevisionService revisionService;
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/vehicles/all")
    public ResponseEntity<List<VehiculoDTO>> getAllVehicles(){
        try {
            List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
            List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
            for (Vehiculo veh : vehiculos) {
                vehiculosDTO.add(toVehiculoDTO(veh));
            }
            return new ResponseEntity<>(vehiculosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicle/{plate}")
    public ResponseEntity<VehiculoDTO> getVehiculoById(@PathVariable String plate){
        try {
            Vehiculo vehiculo = vehiculoService.getVehiculoById(plate);
            return new ResponseEntity<>(toVehiculoDTO(vehiculo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicles/all/total")
    public ResponseEntity<Long> getTotalVehiculos(){
        try {
            long total = vehiculoService.getTotalVehiculos();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicles/in-workshop/total")
    public ResponseEntity<Long> getTotalVehiculosEnTaller(){
        try {
            long total = vehiculoService.getTotalVehiculosEnTaller();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicles/in-workshop")
    public ResponseEntity<List<VehiculoDTO>> getVehiclesInWorkshop(){
        try {
            List<Vehiculo> vehiculos = vehiculoService.getVehiculosEnTaller();
            List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
            for (Vehiculo veh : vehiculos) {
                vehiculosDTO.add(toVehiculoDTO(veh));
            }
            return new ResponseEntity<>(vehiculosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicles/{usuarioId}")
    public ResponseEntity<List<VehiculoDTO>> getVehiculosByIdUsuario(@PathVariable Integer usuarioId){
        try {
            List<Vehiculo> vehiculos = vehiculoService.findByIdUsuario(usuarioId);
            List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
            for (Vehiculo veh : vehiculos) {
                vehiculosDTO.add(toVehiculoDTO(veh));
            }
            return new ResponseEntity<>(vehiculosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicles/{userId}/in-workshop")
    public ResponseEntity<List<VehiculoDTO>> getVehiclesInWorkshopByUserId(@PathVariable Integer userId){
        try {
            List<Vehiculo> vehiculos = vehiculoService.findVehiculosEnTallerByUsuario(userId);
            List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
            for (Vehiculo veh : vehiculos) {
                vehiculosDTO.add(toVehiculoDTO(veh));
            }
            return new ResponseEntity<>(vehiculosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vehicle/new")
    public ResponseEntity<?> addVehiculo(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO){
        try {
            Vehiculo vehiculo = new Vehiculo();
            setVehiculo(vehiculoCreateDTO, vehiculo);
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/usuarios/{usuarioId}/vehicles/{plate}")
    public ResponseEntity<?> addVehiculoToUsuario(@PathVariable Integer usuarioId, @PathVariable String plate){
        try {
            vehiculoService.asociarVehiculo(usuarioId, plate);
            return new ResponseEntity<>(HttpStatus.CREATED);
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
        vehiculoDTO.setFechaProximoMantenimiento(vehiculo.getFechaProximoMantenimiento());
        vehiculoDTO.setStatus(vehiculo.getStatus());
        return vehiculoDTO;
    }

    private void setVehiculo(@RequestBody @Valid VehiculoCreateDTO vehiculoCreateDTO, Vehiculo vehiculo){
        vehiculo.setMatricula(vehiculoCreateDTO.getMatricula());
        vehiculo.setMarca(vehiculoCreateDTO.getMarca());
        vehiculo.setModelo(vehiculoCreateDTO.getModelo());
        vehiculo.setAnyoFabricacion(vehiculoCreateDTO.getAnyoFabricacion());
        vehiculo.setTipoVehiculo(vehiculoCreateDTO.getTipoVehiculo());
        vehiculo.setFechaProximoMantenimiento(vehiculoCreateDTO.getFechaProximoMantenimiento());
        vehiculo.setStatus(0);
        vehiculoService.save(vehiculo);
    }
}
