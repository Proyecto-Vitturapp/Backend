package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.RevisionDTO;
import com.vittur.vitturapp.dtos.create.RevisionCreateDTO;
import com.vittur.vitturapp.model.Revision;
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
public class RevisionController {
    @Autowired
    private RevisionService revisionService;
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/revisiones")
    public List<RevisionDTO> getAllRevisiones(){
        List<RevisionDTO> revisionesDTO = new ArrayList<>();
        List<Revision> revisiones = revisionService.getAllRevisiones();
        for (Revision rev : revisiones) {
            revisionesDTO.add(toRevisionDTO(rev));
        }
        return revisionesDTO;
    }

    @GetMapping("/revisiones/{id}")
    public RevisionDTO getRevisionById(@PathVariable Integer id){
        Revision revision = revisionService.getRevisionById(id);
        return toRevisionDTO(revision);
    }

    @PostMapping("/revisiones")
    public ResponseEntity<?> addRevision(@Valid @RequestBody RevisionCreateDTO revisionCreateDTO){
        try {
            Revision revision = new Revision();
            setRevision(revisionCreateDTO, revision);
            revisionService.save(revision);
            return new ResponseEntity<>(revision, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/revisiones/{id}")
    public ResponseEntity<?> updateRevision(@PathVariable Integer id, @Valid @RequestBody RevisionCreateDTO revision){
        try {
            Revision currentRevision = revisionService.getRevisionById(id);
            setRevision(revision, currentRevision);
            revisionService.save(currentRevision);
            return new ResponseEntity<>(currentRevision, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/revisiones/{id}")
    public ResponseEntity<?> deleteRevision(@PathVariable Integer id){
        try {
            revisionService.deleteRevision(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Revision>(HttpStatus.NOT_FOUND);
        }
    }

    private RevisionDTO toRevisionDTO(Revision revision){
        RevisionDTO revisionDTO = new RevisionDTO();
        revisionDTO.setIdRevision(revision.getIdRevision());
        revisionDTO.setMatricula(revision.getVehiculo() != null ? revision.getVehiculo().getMatricula() : null);
        revisionDTO.setIdCliente(revision.getUsuario() != null ? revision.getUsuario().getIdUsuario() : null);
        revisionDTO.setFechaRevision(revision.getFechaRevision());
        revisionDTO.setKilometrajeActual(revision.getKilometrajeActual());
        revisionDTO.setDiagnosticoResultado(revision.getDiagnosticoResultado());
        revisionDTO.setFechaProximoMantenimiento(revision.getFechaProximoMantenimiento());
        revisionDTO.setImporte(revision.getImporte());
        return revisionDTO;
    }

    private void setRevision(@RequestBody @Valid RevisionCreateDTO revisionCreateDTO, Revision revision) {
        revision.setFechaRevision(revisionCreateDTO.getFechaRevision());
        revision.setKilometrajeActual(revisionCreateDTO.getKilometrajeActual());
        revision.setDiagnosticoResultado(revisionCreateDTO.getDiagnosticoResultado());
        revision.setFechaProximoMantenimiento(revisionCreateDTO.getFechaProximoMantenimiento());
        revision.setImporte(revisionCreateDTO.getImporte());
        revision.setVehiculo(vehiculoService.getVehiculoById(revisionCreateDTO.getMatricula()));
        revision.setUsuario(usuarioService.getUsuarioById(revisionCreateDTO.getIdUsuario()));
    }
}
