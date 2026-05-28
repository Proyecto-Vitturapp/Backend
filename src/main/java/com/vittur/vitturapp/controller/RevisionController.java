package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.RevisionDTO;
import com.vittur.vitturapp.dtos.create.RevisionCreateDTO;
import com.vittur.vitturapp.model.Revision;
import com.vittur.vitturapp.model.Vehiculo;
import com.vittur.vitturapp.services.RevisionService;
import com.vittur.vitturapp.services.UsuarioService;
import com.vittur.vitturapp.services.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RevisionController {
    private final RevisionService revisionService;
    private final VehiculoService vehiculoService;
    private final UsuarioService usuarioService;

    @GetMapping("/revisiones")
    public ResponseEntity<List<RevisionDTO>> getAllRevisiones(){
        try {
            List<Revision> revisiones = revisionService.getAllRevisiones();
            List<RevisionDTO> revisionesDTO = new ArrayList<>();
            for (Revision rev : revisiones) {
                revisionesDTO.add(toRevisionDTO(rev));
            }
            return new ResponseEntity<>(revisionesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/revisiones/{id}")
    public ResponseEntity<RevisionDTO> getRevisionById(@PathVariable Integer id){
        try {
            Revision revision = revisionService.getRevisionById(id);
            return new ResponseEntity<>(toRevisionDTO(revision), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/revisiones/vehiculo/{matricula}")
    public ResponseEntity<List<RevisionDTO>> getRevisionesByMatricula(@PathVariable String matricula){
        try {
            List<Revision> revisiones = revisionService.getRevisionesByMatricula(matricula);
            List<RevisionDTO> revisionesDTO = new ArrayList<>();
            for (Revision rev : revisiones) {
                revisionesDTO.add(toRevisionDTO(rev));
            }
            return new ResponseEntity<>(revisionesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/revisiones/total")
    public ResponseEntity<Long> getTotalRevisiones(){
        try {
            long total = revisionService.getTotalRevisiones();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        revisionDTO.setImporte(revision.getImporte());
        return revisionDTO;
    }

    private void setRevision(@RequestBody @Valid RevisionCreateDTO revisionCreateDTO, Revision revision) {
        revision.setFechaRevision(revisionCreateDTO.getFechaRevision());
        revision.setKilometrajeActual(revisionCreateDTO.getKilometrajeActual());
        revision.setDiagnosticoResultado(revisionCreateDTO.getDiagnosticoResultado());
        revision.setImporte(revisionCreateDTO.getImporte());
        Vehiculo vehiculo = vehiculoService.getVehiculoById(revisionCreateDTO.getMatricula());
        vehiculoService.save(vehiculo);
        revision.setVehiculo(vehiculo);
        revision.setUsuario(usuarioService.getUsuarioById(revisionCreateDTO.getIdUsuario()));
    }
}
